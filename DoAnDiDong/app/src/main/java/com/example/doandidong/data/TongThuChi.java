package com.example.doandidong.data;

import java.util.Date;

public class TongThuChi {
    Double tong;
    Date thang;
    public TongThuChi(){}

    public TongThuChi(Double tong, Date thang) {
        this.tong = tong;
        this.thang = thang;
    }

    public Double getTong() {
        return tong;
    }

    public void setTong(Double tong) {
        this.tong = tong;
    }

    public Date getThang() {
        return thang;
    }

    public void setThang(Date thang) {
        this.thang = thang;
    }
}
