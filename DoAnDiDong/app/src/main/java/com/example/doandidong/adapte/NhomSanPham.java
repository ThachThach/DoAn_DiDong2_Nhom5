package com.example.doandidong.adapte;

public class NhomSanPham {
    private String tenNhom;
    private  int img;
    private String idNhomSanPham;
    private Boolean isCheck = false;

    public String getIdNhomSanPham() {
        return idNhomSanPham;
    }

    public void setIdNhomSanPham(String idNhomSanPham) {
        this.idNhomSanPham = idNhomSanPham;
    }

    public Boolean getCheck() {
        return isCheck;
    }

    public void setCheck(Boolean check) {
        isCheck = check;
    }



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
