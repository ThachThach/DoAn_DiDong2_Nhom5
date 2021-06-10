package com.example.doandidong.data;

public class PhongBan {
    private int khuvuc;
    private String tenban;
    private boolean trangthai;

    public int getKhuvuc() {
        return khuvuc;
    }

    public void setKhuvuc(int khuvuc) {
        this.khuvuc = khuvuc;
    }

    public String getTenban() {
        return tenban;
    }

    public void setTenban(String tenban) {
        this.tenban = tenban;
    }

    public boolean isTrangthai() {
        return trangthai;
    }

    public void setTrangthai(boolean trangthai) {
        this.trangthai = trangthai;
    }

    public PhongBan(int khuvuc, String tenban, boolean trangthai) {
        this.khuvuc = khuvuc;
        this.tenban = tenban;
        this.trangthai = trangthai;
    }

    public PhongBan() {
    }
}
