package com.example.mikez.festpaycustomer.network;

/**
 * Created by mikez on 9/11/2017.
 */

public class RegisterModel {

    private String email;

    private String password;

    public RegisterModel(String email, String password){

        setEmail(email);
        setPassword(password);
    }

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
