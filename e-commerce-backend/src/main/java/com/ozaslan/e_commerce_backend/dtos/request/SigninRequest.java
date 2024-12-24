package com.ozaslan.e_commerce_backend.dtos.request;

import lombok.Data;
import lombok.Getter;

@Getter
@Data

public class SigninRequest {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}