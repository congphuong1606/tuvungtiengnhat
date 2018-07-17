package com.phuongnv.tuvungtiengnhat.data;

/**
 * Created by admin on 3/11/2018.
 */

public class TuVung {
    int id;
    String tuvung;
    String romaji;
    String sound;
    String kanj;
    String hanViet;
    String nghia;
    int bai;

    public TuVung() {
    }

    public TuVung(int id, String tuvung, String romaji, String sound, String kanj, String hanViet, String nghia, int bai) {
        this.id = id;
        this.tuvung = tuvung;
        this.romaji = romaji;
        this.sound = sound;
        this.kanj = kanj;
        this.hanViet = hanViet;
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

    public String getRomaji() {
        return romaji;
    }

    public void setRomaji(String romaji) {
        this.romaji = romaji;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String getKanj() {
        return kanj;
    }

    public void setKanj(String kanj) {
        this.kanj = kanj;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
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
