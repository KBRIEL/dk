package com.dk.modelo.Security;


public class AuthRequest {
    private String email;
    private String password;

    // getters and setters


    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}