package com.ozaslan.e_commerce_backend.config;

public class PasswordValidator {
    public static boolean isPasswordSecure(String password) {
        if (password.length() < 8) {
            throw new RuntimeException("Şifre en az 8 karakter olmalıdır.");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new RuntimeException("Şifre en az bir büyük harf içermelidir.");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new RuntimeException("Şifre en az bir küçük harf içermelidir.");
        }
        if (!password.matches(".*\\d.*")) {
            throw new RuntimeException("Şifre en az bir rakam içermelidir.");
        }
        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new RuntimeException("Şifre en az bir özel karakter içermelidir.");
        }
        return true;
    }

}
