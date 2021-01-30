package com.example.ganache;

public class Category {
    private int cateId;
    private String cateName;
    private String cateidImage;
    private String cateIntro;

    public Category(int cateId, String cateName, String cateidImage, String cateIntro) {
        this.cateId = cateId;
        this.cateName = cateName;
        this.cateidImage = cateidImage;
        this.cateIntro = cateIntro;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateidImage() {
        return cateidImage;
    }

    public void setCateidImage(String cateidImage) {
        this.cateidImage = cateidImage;
    }

    public String getCateIntro() {
        return cateIntro;
    }

    public void setCateIntro(String cateIntro) {
        this.cateIntro = cateIntro;
    }
}
