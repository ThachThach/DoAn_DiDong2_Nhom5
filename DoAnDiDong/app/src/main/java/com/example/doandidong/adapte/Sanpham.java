package com.example.doandidong.adapte;

public class Sanpham {
    public Sanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
    }

    public Sanpham(String tenSanpham, Double giaSanpham, int image, String nhomSanPham) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.image = image;
        this.nhomSanPham = nhomSanPham;
    }

    String tenSanpham;
    Double giaSanpham;
    int image;
    String donVitinh;
    String nhomSanPham;
    Double vonSanPham;
    String maSanPham;

    public String getIdSanPham() {
        return IdSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        IdSanPham = idSanPham;
    }

    String IdSanPham;

    public Boolean getCheckSP() {
        return isCheckSP;
    }

    public void setCheckSP(Boolean checkSP) {
        isCheckSP = checkSP;
    }

    Boolean isCheckSP = false;

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

    public String setNhomSanPham(String nhomSanPham) {
        this.nhomSanPham = nhomSanPham;
        return nhomSanPham;
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

    public String setTenSanpham(String tenSanpham) {
        this.tenSanpham = tenSanpham;
        return tenSanpham;
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
