package com.example.doandidong.data;

public class ThuChi {
    private Double tongThu;
    private Double tongVon;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getTongThu() {
        return tongThu;
    }

    public void setTongThu(Double tongThu) {
        this.tongThu = tongThu;
    }

    public Double getTongVon() {
        return tongVon;
    }

    public void setTongVon(Double tongVon) {
        this.tongVon = tongVon;
    }

    public ThuChi(Double tongThu, Double tongVon, String id) {
        this.tongThu = tongThu;
        this.tongVon = tongVon;
        this.id = id;
    }

    public ThuChi() {
    }
}
