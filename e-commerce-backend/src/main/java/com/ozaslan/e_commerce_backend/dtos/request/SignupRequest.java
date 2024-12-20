package com.ozaslan.e_commerce_backend.dtos.request;



import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class SignupRequest {

    @Email(message = "Geçerli bir e-posta adresi giriniz.")
    @NotBlank(message = "E-posta boş olamaz.")
    private String email;


    @NotBlank(message = "Şifre boş olamaz.")
    private String password;

    @NotBlank(message = "İsim boş olamaz.")
    private String firstName;

    @NotBlank(message = "Soyisim boş olamaz.")
    private String lastName;


    private String phoneNumber;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
