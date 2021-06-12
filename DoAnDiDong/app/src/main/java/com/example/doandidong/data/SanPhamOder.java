package com.example.doandidong.data;

public class SanPhamOder {
    private String nhomSanPham;
    private String tenSP;
    private double giaBan;
    private double giaLe;
    private double soLuong;

    public SanPhamOder(String nhomSanPham,String tenSP, double giaBan, double giaLe) {
        this.nhomSanPham = nhomSanPham;
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

    public String getNhomSanPham() {
        return nhomSanPham;
    }

    public void setNhomSanPham(String nhomSanPham) {
        this.nhomSanPham = nhomSanPham;
    }
}
