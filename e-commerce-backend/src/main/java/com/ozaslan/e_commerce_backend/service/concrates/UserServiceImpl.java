package com.ozaslan.e_commerce_backend.service.concrates;

import com.ozaslan.e_commerce_backend.config.JwtUtil;
import com.ozaslan.e_commerce_backend.exceptions.UserException;
import com.ozaslan.e_commerce_backend.model.User;
import com.ozaslan.e_commerce_backend.repository.UserRepository;
import com.ozaslan.e_commerce_backend.service.abstracts.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtProvider;

    public UserServiceImpl(UserRepository userRepository, JwtUtil jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserException("User not found with id :" + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.extractEmailFromResetToken(jwt);


        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException("User not found with email " + email));

        return user;


    }
}
