package com.example.mikez.festpaycustomer.localdatabase;

/**
 * Created by mikez on 8/1/2017.
 */

class User {

    private int Id;
    private String email;
    private String password;

    User(int Id, String email, String password) {

        this.Id = Id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return Id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {

        return password;
    }
}
