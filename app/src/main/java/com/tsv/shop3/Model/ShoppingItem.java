package com.tsv.shop3.Model;

public class ShoppingItem {
    int id;
    String name;

    public ShoppingItem() {
    }

    public ShoppingItem(int id, String name) {
        this.id = id;
        this.name = name;
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


}
