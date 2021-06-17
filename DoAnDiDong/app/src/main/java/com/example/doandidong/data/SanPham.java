package com.example.doandidong.data;

public class SanPham {
    String tenSanpham;
    Double giaSanpham;
    int image;
    String donVitinh;
    String nhomSanPham;
    Double vonSanPham;
    String maSanPham;
    Boolean isCheckSP = false;
    String IdSanPham;

    public Boolean getCheckSP() {
        return isCheckSP;
    }

    public void setCheckSP(Boolean checkSP) {
        isCheckSP = checkSP;
    }

    public String getIdSanPham() {
        return IdSanPham;
    }

    public void setIdSanPham(String idSanPham) {
        IdSanPham = idSanPham;
    }

    public SanPham(){}

    public SanPham(String tenSanpham, Double giaSanpham, int image) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.image = image;
    }

    public SanPham(String tenSanpham, Double giaSanpham, int image, String donVitinh, String nhomSanPham, Double vonSanPham, String maSanPham) {
        this.tenSanpham = tenSanpham;
        this.giaSanpham = giaSanpham;
        this.image = image;
        this.donVitinh = donVitinh;
        this.nhomSanPham = nhomSanPham;
        this.vonSanPham = vonSanPham;
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

    public Double setGiaSanpham(Double giaSanpham) {
        this.giaSanpham = giaSanpham;
        return giaSanpham;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
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

    public Double setVonSanPham(Double vonSanPham) {
        this.vonSanPham = vonSanPham;

        return vonSanPham;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }


}
