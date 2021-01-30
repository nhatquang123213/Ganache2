package com.example.ganache;

import java.io.Serializable;

public class Product_Search implements Serializable {


    int id ;
    String price;
    int id_cate;
    String name;
    String idimg;
    String detail;

    public Product_Search(int id, int id_cate, String name, String price, String idimg, String detail) {
        this.id = id;
        this.price = price;
        this.id_cate = id_cate;
        this.name = name;
        this.idimg = idimg;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getId_cate() {
        return id_cate;
    }

    public void setId_cate(int id_cate) {
        this.id_cate = id_cate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdimg() {
        return idimg;
    }

    public void setIdimg(String idimg) {
        this.idimg = idimg;
    }
}
