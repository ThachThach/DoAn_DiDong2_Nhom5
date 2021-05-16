package com.example.doandidong.data;

public class Admin {
    private String email;
    private String admin;

    public Admin() {
    }

    public Admin(String email, String admin) {
        this.email = email;
        this.admin = admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
