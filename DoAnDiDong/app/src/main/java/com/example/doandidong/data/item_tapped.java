package com.example.doandidong.data;

public class item_tapped {
    private String banKhuVuc;
    private int viTri;

    public item_tapped(String banKhuVuc, int viTri) {
        this.banKhuVuc = banKhuVuc;
        this.viTri = viTri;
    }

    public item_tapped() {
    }

    public String getBanKhuVuc() {
        return banKhuVuc;
    }

    public void setBanKhuVuc(String banKhuVuc) {
        this.banKhuVuc = banKhuVuc;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }
}