package com.phuongnv.tuvungtiengnhat.data;

/**
 * Created by admin on 3/11/2018.
 */

public class TuVung {
    int id;
    String tuvung;
    String kanj;
    String nghia;
    int bai;

    public TuVung() {
    }

    public TuVung(int id, String tuvung, String kanj, String nghia, int bai) {
        this.id = id;
        this.tuvung = tuvung;
        this.kanj = kanj;
        this.nghia = nghia;
        this.bai = bai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTuvung() {
        return tuvung;
    }

    public void setTuvung(String tuvung) {
        this.tuvung = tuvung;
    }

    public String getKanj() {
        return kanj;
    }

    public void setKanj(String kanj) {
        this.kanj = kanj;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public int getBai() {
        return bai;
    }

    public void setBai(int bai) {
        this.bai = bai;
    }
}
