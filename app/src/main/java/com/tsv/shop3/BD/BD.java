package com.tsv.shop3.BD;

import com.tsv.shop3.Model.Entity.ShoppingItem;

import java.util.ArrayList;
import java.util.List;

public class BD {

    private static BD instance;

    private List<ShoppingItem> list;

    private BD() {
        list = new ArrayList<>();
        list.add(new ShoppingItem(1, "max1"));
        list.add(new ShoppingItem(2, "max2"));
        list.add(new ShoppingItem(3, "max3"));
    }

    public static BD getInstance() {
        if (instance == null) {
            instance = new BD();
        }
        return instance;
    }

    public boolean addToBd(ShoppingItem shoppingItem) {
        try {
            list.add(shoppingItem);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public ShoppingItem getById(int id) {
        for (ShoppingItem i : list) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public boolean remove(int id) {
        boolean b = false;
        for (ShoppingItem i : list) {
            if (i.getId() == id) {
                list.remove(i);
                b = true;
            }
        }
        return b;
    }

    public List<ShoppingItem> getList(){
        return list;
    }


}
