package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetService {

    private final JwtUtil jwtUtil;
    private final JavaMailSender mailSender;

    @Autowired
    public PasswordResetService(JwtUtil jwtUtil, JavaMailSender mailSender) {
        this.jwtUtil = jwtUtil;
        this.mailSender = mailSender;
    }

    public void sendPasswordResetEmail(String email) {
        String token = jwtUtil.generateToken(email);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Şifre Sıfırlama Talebi");
        message.setText("Şifrenizi sıfırlamak için şu linke tıklayın: \n"
                + "http://localhost:5454/auth/reset-password?token=" + token);

        mailSender.send(message);
    }
}