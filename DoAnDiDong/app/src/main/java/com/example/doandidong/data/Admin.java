package com.example.doandidong.data;

public class Admin {
    private String email;
    private String userLevel;

    public Admin() {
    }

    public Admin(String email, String admin) {
        this.email = email;
        this.userLevel = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
}
