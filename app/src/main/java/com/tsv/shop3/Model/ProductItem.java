package com.tsv.shop3.Model;

public class ProductItem {
    private int id;
    private String name;
    private String size;
    private double price;
    private boolean marked;

    public ProductItem() {
    }

    public ProductItem(int id, String name, String size, double price, boolean marked) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.marked = marked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
