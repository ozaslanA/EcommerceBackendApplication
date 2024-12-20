package com.ozaslan.e_commerce_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentInformation {


    @Column(name = "cardholder_name", nullable = false) // nullable = false ile boş değer engellenir
    private String cardholderName;

    @Column(name = "card_number", nullable = false, unique = true) // unique = true ile benzersiz kısıt eklenir
    private String cardNumber;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(name = "cvv", nullable = false)
    private String cvv;
}
