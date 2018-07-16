package com.phuongnv.tuvungtiengnhat.data;

import java.io.Serializable;

/**
 * Created by Ominext on 7/12/2018.
 */
@SuppressWarnings("serial")
public class Kanj implements Serializable {
    int id;
    String kanj;
    String negetive;
    String soundkun;
    String vietnam;
    String meaning;


    public Kanj() {
    }

    public Kanj(int id, String kanj, String negetive, String soundkun, String vietnam, String meaning) {
        this.id = id;
        this.kanj = kanj;
        this.negetive = negetive;
        this.soundkun = soundkun;
        this.vietnam = vietnam;
        this.meaning = meaning;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKanj() {
        return kanj;
    }

    public void setKanj(String kanj) {
        this.kanj = kanj;
    }

    public String getNegetive() {
        return negetive;
    }

    public void setNegetive(String negetive) {
        this.negetive = negetive;
    }

    public String getSoundkun() {
        return soundkun;
    }

    public void setSoundkun(String soundkun) {
        this.soundkun = soundkun;
    }

    public String getVietnam() {
        return vietnam;
    }

    public void setVietnam(String vietnam) {
        this.vietnam = vietnam;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
