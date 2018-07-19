package com.phuongnv.tuvungtiengnhat.data;

/**
 * Created by Ominext on 7/19/2018.
 */

public class Kaiwa {
     int id;
     int isTitle;
     String userJ;
     String userR;
     String kaiwaJ;
     String kaiwaR;
     String kaiwaV;
     int lesson;

    public Kaiwa() {
    }

    public Kaiwa(int id, int isTitle, String userJ, String userR, String kaiwaJ, String kaiwaR, String kaiwaV, int lesson) {
        this.id = id;
        this.isTitle = isTitle;
        this.userJ = userJ;
        this.userR = userR;
        this.kaiwaJ = kaiwaJ;
        this.kaiwaR = kaiwaR;
        this.kaiwaV = kaiwaV;
        this.lesson = lesson;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(int isTitle) {
        this.isTitle = isTitle;
    }

    public String getUserJ() {
        return userJ;
    }

    public void setUserJ(String userJ) {
        this.userJ = userJ;
    }

    public String getUserR() {
        return userR;
    }

    public void setUserR(String userR) {
        this.userR = userR;
    }

    public String getKaiwaJ() {
        return kaiwaJ;
    }

    public void setKaiwaJ(String kaiwaJ) {
        this.kaiwaJ = kaiwaJ;
    }

    public String getKaiwaR() {
        return kaiwaR;
    }

    public void setKaiwaR(String kaiwaR) {
        this.kaiwaR = kaiwaR;
    }

    public String getKaiwaV() {
        return kaiwaV;
    }

    public void setKaiwaV(String kaiwaV) {
        this.kaiwaV = kaiwaV;
    }

    public int getLesson() {
        return lesson;
    }

    public void setLesson(int lesson) {
        this.lesson = lesson;
    }
}
