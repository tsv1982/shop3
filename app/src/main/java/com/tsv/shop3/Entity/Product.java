package com.tsv.shop3.Entity;

public class Product {
    private String id;
    private String name;
    private String size;
    private double price;
    private boolean marked;
    private String sizeMarked;
    private String UrlPictureByFireBase;


    public Product() {
    }

    public Product(String id, String name, String size, double price, String UrlPicture) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.marked = false;
        this.sizeMarked = "не выбранное";
        this.UrlPictureByFireBase = UrlPicture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public String getSizeMarked() {
        return sizeMarked;
    }

    public void setSizeMarked(String sizeMarked) {
        this.sizeMarked = sizeMarked;
    }

    public String getUrlPictureByFireBase() {
        return UrlPictureByFireBase;
    }

    public void setUrlPictureByFireBase(String urlPictureByFireBase) {
        UrlPictureByFireBase = urlPictureByFireBase;
    }
}
