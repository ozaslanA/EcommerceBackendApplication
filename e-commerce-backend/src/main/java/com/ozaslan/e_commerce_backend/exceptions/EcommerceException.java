package com.ozaslan.e_commerce_backend.exceptions;

import org.springframework.http.HttpStatus;

public class EcommerceException extends RuntimeException {

    private HttpStatus httpStatus;

    public EcommerceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

}
