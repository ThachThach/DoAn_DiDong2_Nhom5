package com.example.doandidong.data;

public class SanPhamOder {
    private String nhomSanPham;
    private String tenSP;
    private double giaBan;
    private double giaVon;
    private double soLuong;
    private String banKhuVuc;

    public SanPhamOder(double giaBan, double giaVon) {
        this.giaBan = giaBan;
        this.giaVon = giaVon;
    }

    public SanPhamOder(String nhomSanPham, String tenSP, double giaVon, double giaBan) {
        this.nhomSanPham = nhomSanPham;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.giaVon = giaVon;
        this.soLuong = 0;
    }

    public SanPhamOder(String tenSP, double soLuong, String banKhuVuc) {
        this.nhomSanPham = "null";
        this.tenSP = tenSP;
        this.giaBan = 0;
        this.giaVon = 0;
        this.banKhuVuc = banKhuVuc;
        this.soLuong = soLuong;
    }

    public SanPhamOder(String banKhuVuc) {
        this.nhomSanPham = "nullssss";
        this.tenSP = "nullssss";
        this.giaBan = 0;
        this.giaVon = 0;
        this.banKhuVuc = banKhuVuc;
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

    public double getGiaVon() {
        return giaVon;
    }

    public void setGiaVon(double giaLe) {
        this.giaVon = giaLe;
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

    public String getBanKhuVuc() {
        return banKhuVuc;
    }

    public void setBanKhuVuc(String banKhuVuc) {
        this.banKhuVuc = banKhuVuc;
    }
}