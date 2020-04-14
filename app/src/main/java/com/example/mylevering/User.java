package com.example.mylevering;

public class User {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User() {

    }

    public User(String userId, String fn, String ln, String em, String pwd) {
        this.userId = userId;
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.password = pwd;
    }
}
