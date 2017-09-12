package com.example.mikez.festpaycustomer.network;

/**
 * Created by mikez on 9/11/2017.
 */

public class LoginModel {

    private String email;

    private String password;

    private boolean rememberMe;


    public LoginModel(String email, String password){

        setEmail(email);
        setPassword(password);
        setRememberMe(false);
    }

    public String getEmail(){
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRememberMe() {
        return rememberMe;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
