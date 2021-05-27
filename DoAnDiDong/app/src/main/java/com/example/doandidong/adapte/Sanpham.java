package com.example.doandidong.adapte;

public class Sanpham {

    String tenSanpham;
    Double giaSanpham;
    int image;

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
