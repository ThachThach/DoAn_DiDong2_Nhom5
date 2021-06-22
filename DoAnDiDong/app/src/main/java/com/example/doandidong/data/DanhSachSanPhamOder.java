package com.example.doandidong.data;

import java.util.ArrayList;
import java.util.Date;

public class DanhSachSanPhamOder {
        private String tenban;
        private String khuVuc;
        private Date time;
        private ArrayList<SanPhamOder> listSP;

        public DanhSachSanPhamOder() {
        }

        public DanhSachSanPhamOder(String tenban, String khuVuc, ArrayList<SanPhamOder> listSP) {
            this.tenban = tenban;
            this.khuVuc = khuVuc;
            this.listSP = listSP;
        }

        public DanhSachSanPhamOder(String tenban, String khuVuc, Date time, ArrayList<SanPhamOder> listSP) {
            this.tenban = tenban;
            this.khuVuc = khuVuc;
            this.time = time;
            this.listSP = listSP;
        }

        public String getTenban() {
            return tenban;
        }

        public void setTenban(String tenban) {
            this.tenban = tenban;
        }

        public String getKhuVuc() {
            return khuVuc;
        }

        public void setKhuVuc(String khuVuc) {
            this.khuVuc = khuVuc;
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public ArrayList<SanPhamOder> getListSP() {
            return listSP;
        }

        public void setListSP(ArrayList<SanPhamOder> listSP) {
            this.listSP = listSP;
        }
    }
