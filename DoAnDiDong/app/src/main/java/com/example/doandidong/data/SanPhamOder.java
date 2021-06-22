package com.example.doandidong.data;

public class SanPhamOder {
    private String nhomSanPham;
    private String tenSP;
    private double giaBan;
    private double giaLe;
    private double soLuong;
    private String banKhuVuc;

    public SanPhamOder(String nhomSanPham,String tenSP, double giaBan, double giaLe) {
        this.nhomSanPham = nhomSanPham;
        this.tenSP = tenSP;
        this.giaBan = giaBan;
        this.giaLe = giaLe;
        this.soLuong = 0;
    }

    public SanPhamOder(String tenSP, double soLuong, String banKhuVuc) {
        this.nhomSanPham = "null";
        this.tenSP = tenSP;
        this.giaBan = 0;
        this.giaLe = 0;
        this.banKhuVuc = banKhuVuc;
        this.soLuong = soLuong;
    }

    public SanPhamOder(String banKhuVuc) {
        this.nhomSanPham = "nullssss";
        this.tenSP = "nullssss";
        this.giaBan = 0;
        this.giaLe = 0;
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

    public String getBanKhuVuc() {
        return banKhuVuc;
    }

    public void setBanKhuVuc(String banKhuVuc) {
        this.banKhuVuc = banKhuVuc;
    }
}
