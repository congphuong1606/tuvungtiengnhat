package com.phuongnv.tuvungtiengnhat.data;

/**
 * Created by Ominext on 7/12/2018.
 */

public class KanjExample {
    int id;
    String word;
    String hiragana;
    String hanViet;
    String meaning;
    String kanjId;

    public KanjExample() {
    }

    public KanjExample(int id, String word, String hiragana, String hanViet, String meaning, String kanjId) {
        this.id = id;
        this.word = word;
        this.hiragana = hiragana;
        this.hanViet = hanViet;
        this.meaning = meaning;
        this.kanjId = kanjId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getHanViet() {
        return hanViet;
    }

    public void setHanViet(String hanViet) {
        this.hanViet = hanViet;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getKanjId() {
        return kanjId;
    }

    public void setKanjId(String kanjId) {
        this.kanjId = kanjId;
    }
}
