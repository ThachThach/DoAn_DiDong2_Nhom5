package com.example.doandidong.data;

public class NhanVien {
    private String email;
    private String chucvu;
    private String email_admin;
    private String name;
    private String calam;

    public NhanVien(String email, String chucvu, String email_admin, String name) {
        this.email = email;
        this.chucvu = chucvu;
        this.email_admin = email_admin;
        this.name = name;
    }

    public NhanVien(String email, String chucvu, String email_admin, String name, String calam) {
        this.email = email;
        this.chucvu = chucvu;
        this.email_admin = email_admin;
        this.name = name;
        this.calam = calam;
    }

    public NhanVien(){}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getChucvu() {
        return chucvu;
    }

    public void setChucvu(String chucvu) {
        this.chucvu = chucvu;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCalam() {
        return calam;
    }

    public void setCalam(String calam) {
        this.calam = calam;
    }
}