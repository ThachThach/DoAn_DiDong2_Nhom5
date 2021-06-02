package com.example.doandidong.adapte;

public class NhomSanPham {
    private String tenNhom;
    private  int img;

    public NhomSanPham(){}

    public NhomSanPham(String tenNhom){
        this.tenNhom = tenNhom;
    }

    public NhomSanPham(String tenNhom, int img) {
        this.tenNhom = tenNhom;
        this.img = img;
    }

    public String getTenNhom() {
        return tenNhom;
    }

    public String setTenNhom(String tenNhom) {
        this.tenNhom = tenNhom;
        return tenNhom;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }


}
