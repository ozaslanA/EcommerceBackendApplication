package com.ozaslan.e_commerce_backend.dtos.request;

public class ResetPasswordRequest {

    private String newPassword;

    public ResetPasswordRequest(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
