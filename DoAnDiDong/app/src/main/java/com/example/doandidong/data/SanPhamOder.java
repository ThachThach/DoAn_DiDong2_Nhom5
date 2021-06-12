package com.example.doandidong.data;

public class SanPhamOder {
    private String tenSP;
    private double giaBan;
    private double giaLe;
    private double soLuong;

    public SanPhamOder(String tenSP, double giaBan, double giaLe) {
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.giaLe = giaLe;
        this.soLuong = 0;
    }

    public SanPhamOder() {
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(double giaBan) {
        this.giaBan = giaBan;
    }

    public double getGiaLe() {
        return giaLe;
    }

    public void setGiaLe(double giaLe) {
        this.giaLe = giaLe;
    }

    public double getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(double soLuong) {
        this.soLuong = soLuong;
    }
}
