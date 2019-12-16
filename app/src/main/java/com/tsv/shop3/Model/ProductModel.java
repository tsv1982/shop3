package com.tsv.shop3.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ProductModel implements IproductModel{
    private String name;
    private String size;
    private double price;
    private String imageProduct;
    private boolean  checkProduct;
    @PrimaryKey
    private int ID;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSize() {
        return size;
    }

    @Override
    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String getImageProduct() {
        return imageProduct;
    }

    @Override
    public void setImageProduct(String imageProduct) {
        this.imageProduct = imageProduct;
    }

    @Override
    public boolean getCheckProduct() {
        return checkProduct;
    }

    @Override
    public void setCheckProduct(boolean checkProduct) {
        this.checkProduct = checkProduct;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public void setID(int ID) {
        this.ID = ID;
    }
}
