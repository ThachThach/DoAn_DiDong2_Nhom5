package com.example.doandidong.adapte;

public class Sanpham {
    String tenSanpham;
    Double giaSanpham;
    int image;
    String donVitinh;
    String nhomSanPham;
    Double vonSanPham;
    String maSanPham;

    public Sanpham(String tenSanpham, Double giaSanpham, String donVitinh, String nhomSanPham, Double vonSanPham, String maSanPham) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.donVitinh = donVitinh;
        this.nhomSanPham = nhomSanPham;
        this.vonSanPham = vonSanPham;
        this.maSanPham = maSanPham;
    }


    public String getDonVitinh() {
        return donVitinh;
    }

    public void setDonVitinh(String donVitinh) {
        this.donVitinh = donVitinh;
    }

    public String getNhomSanPham() {
        return nhomSanPham;
    }

    public void setNhomSanPham(String nhomSanPham) {
        this.nhomSanPham = nhomSanPham;
    }

    public Double getVonSanPham() {
        return vonSanPham;
    }

    public void setVonSanPham(Double vonSanPham) {
        this.vonSanPham = vonSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }



    public String getTenSanpham() {
        return tenSanpham;
    }

    public void setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public Double getGiaSanpham() {
        return giaSanpham;
    }

    public void setGiaSanpham(Double giaSanpham) {
        this.giaSanpham = giaSanpham;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public Sanpham(){

    }
    public Sanpham(String tenSanpham, Double giaSanpham, int image) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.image = image;
    }
}
