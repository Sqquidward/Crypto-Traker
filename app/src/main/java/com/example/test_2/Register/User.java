package com.example.test_2.Register;

public class User {
    public String name;
    public String lastname;
    public String email;
    public String password;

    public User() {
    }

    public User(String etName, String etLastname, String etEmail, String password) {
        this.name = etName;
        this.lastname = etLastname;
        this.email = etEmail;
        this.password = password;
    }
}
