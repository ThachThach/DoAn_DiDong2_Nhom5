package com.example.doandidong.adapte;

public class Bepbar {

    String tennguoioder;
    String tenban;
    String thoigian;
    int soluong;
    String tensanpham;
    String khuvuc;

    public Bepbar(String tennguoioder, String tenban, String thoigian, int soluong, String tensanpham, String khuvuc) {
        this.tennguoioder = tennguoioder;
        this.tenban = tenban;
        this.thoigian = thoigian;
        this.soluong = soluong;
        this.tensanpham = tensanpham;
        this.khuvuc = khuvuc;
    }

    public String getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(String khuvuc) {
        this.khuvuc = khuvuc;
    }



    public String getTennguoioder() {
        return tennguoioder;
    }

    public void setTennguoioder(String tennguoioder) {
        this.tennguoioder = tennguoioder;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public String getTensanpham() {
        return tensanpham;
    }

    public void setTensanpham(String tensanpham) {
        this.tensanpham = tensanpham;
    }


    public Bepbar() {
    }
}
