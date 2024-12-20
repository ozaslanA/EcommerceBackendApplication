package com.ozaslan.e_commerce_backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String role;
    private String mobile;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Addres> addres=new ArrayList<>();

    @Embedded
    @ElementCollection
    @CollectionTable(name = "payment_information",joinColumns = @JoinColumn(name = "user_id"))
    private List<PaymentInformation> paymentInformations=new ArrayList<>();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Rating>ratings=new ArrayList<>();


    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Review> rivews=new ArrayList<>();

    private LocalDateTime createdAt;

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getMobile() {
        return mobile;
    }

    public List<Addres> getAddres() {
        return addres;
    }

    public List<PaymentInformation> getPaymentInformations() {
        return paymentInformations;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public List<Review> getRivews() {
        return rivews;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setAddres(List<Addres> addres) {
        this.addres = addres;
    }

    public void setPaymentInformations(List<PaymentInformation> paymentInformations) {
        this.paymentInformations = paymentInformations;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public void setRivews(List<Review> rivews) {
        this.rivews = rivews;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
