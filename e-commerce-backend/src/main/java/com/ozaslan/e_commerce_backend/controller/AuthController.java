package com.ozaslan.e_commerce_backend.controller;

import com.ozaslan.e_commerce_backend.config.JwtUtil;
import com.ozaslan.e_commerce_backend.config.PasswordValidator;
import com.ozaslan.e_commerce_backend.dtos.request.ForgotPasswordRequest;
import com.ozaslan.e_commerce_backend.dtos.request.ResetPasswordRequest;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.UserRepository;

import com.ozaslan.e_commerce_backend.dtos.request.SigninRequest;
import com.ozaslan.e_commerce_backend.dtos.request.SignupRequest;
import com.ozaslan.e_commerce_backend.service.abstracts.CustomerUserServiceImpl;

import com.ozaslan.e_commerce_backend.service.concrates.PasswordResetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final PasswordResetService passwordResetService;

  private final CustomerUserServiceImpl customerUserServiceImpl;
    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, PasswordResetService passwordResetService,CustomerUserServiceImpl customerUserServiceImpl) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.passwordResetService = passwordResetService;
        this.customerUserServiceImpl=customerUserServiceImpl;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new RuntimeException("Bu e-posta ile kayıtlı bir kullanıcı zaten var.Lütfen farklı bir e-posta ile kayıt olun veya parolamı unuttum seçeneğinden ilerleyiniz.");
        }

       PasswordValidator.isPasswordSecure(request.getPassword());

        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        userRepository.save(newUser);

        return "Kullanıcı başarıyla kaydedildi.";
    }

    @PostMapping("/signin")
    public String signin(@RequestBody SigninRequest request) {
        User existingUser = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        if (!passwordEncoder.matches(request.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Geçersiz e-posta veya şifre.");
        }

        String token = jwtUtil.generateToken(existingUser.getEmail());
        return "JWT " + token;
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));

        // E-posta gönderme servisini çağırıyoruz
        passwordResetService.sendPasswordResetEmail(request.getEmail());

        return ResponseEntity.ok("Şifre sıfırlama bağlantısı e-posta adresinize gönderildi.");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam("token") String token, @RequestBody ResetPasswordRequest request) {
        String email = jwtUtil.extractEmailFromResetToken(token);

        // Token geçerli mi kontrol et
        if (jwtUtil.isTokenExpired(token)) {
            return ResponseEntity.status(400).body("Token süresi dolmuş.");
        }

        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı."));
        PasswordValidator.isPasswordSecure(request.getNewPassword());

        // Şifreyi sıfırlıyoruz
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok("Şifreniz başarıyla sıfırlandı.");
    }
}






