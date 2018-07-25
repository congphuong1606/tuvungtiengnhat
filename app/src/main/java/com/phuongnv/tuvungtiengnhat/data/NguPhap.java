package com.phuongnv.tuvungtiengnhat.data;

/**
 * Created by Ominext on 7/20/2018.
 */

public class NguPhap {
    int id;
    String nguPhap;
    String nghia;
    String viDu;
    int Lesson;

    public NguPhap(int id, String nguPhap, String nghia, String viDu, int lesson) {
        this.id = id;
        this.nguPhap = nguPhap;
        this.nghia = nghia;
        this.viDu = viDu;
        Lesson = lesson;
    }

    public NguPhap() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNguPhap() {
        return nguPhap;
    }

    public void setNguPhap(String nguPhap) {
        this.nguPhap = nguPhap;
    }

    public String getNghia() {
        return nghia;
    }

    public void setNghia(String nghia) {
        this.nghia = nghia;
    }

    public String getViDu() {
        return viDu;
    }

    public void setViDu(String viDu) {
        this.viDu = viDu;
    }

    public int getLesson() {
        return Lesson;
    }

    public void setLesson(int lesson) {
        Lesson = lesson;
    }
}
