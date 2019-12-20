package com.tsv.shop3.Model;

public class ShoppingListItem {
     private int id;
     private String name;
     private double size;
     private double price;
     private String pathPicture;


    public ShoppingListItem() {
    }

    public ShoppingListItem(int id, String name, double size, double price, String pathPicture) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.price = price;
        this.pathPicture = pathPicture;
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

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPathPicture() {
        return pathPicture;
    }

    public void setPathPicture(String pathPicture) {
        this.pathPicture = pathPicture;
    }
}
