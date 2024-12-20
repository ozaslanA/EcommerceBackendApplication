package com.ozaslan.e_commerce_backend.dtos.request;

import lombok.Data;
import lombok.Getter;

@Getter
@Data

public class SigninRequest {

    private String email;
    private String password;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SigninRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

}