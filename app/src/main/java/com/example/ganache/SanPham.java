package com.example.ganache;

import java.io.Serializable;

public class SanPham implements Serializable{
    private int idSp;
    private String imgSp;
    private String ten;
    private int idCate_sp;
    private String price_sp;
    private String bestsell;
    private String detail;

    public SanPham(int idSp, int idCate_sp, String ten, String detail, String price_sp,String imgSp,  String bestsell) {
        this.idSp = idSp;
        this.imgSp = imgSp;
        this.ten = ten;
        this.idCate_sp = idCate_sp;
        this.price_sp = price_sp;
        this.bestsell = bestsell;
        this.detail = detail;
    }

    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }

    public String getImgSp() {
        return imgSp;
    }

    public void setImgSp(String imgSp) {
        this.imgSp = imgSp;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public int getIdCate_sp() {
        return idCate_sp;
    }

    public void setIdCate_sp(int idCate_sp) {
        this.idCate_sp = idCate_sp;
    }

    public String getPrice_sp() {
        return price_sp;
    }

    public void setPrice_sp(String price_sp) {
        this.price_sp = price_sp;
    }

    public String getBestsell() {
        return bestsell;
    }

    public void setBestsell(String bestsell) {
        this.bestsell = bestsell;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
