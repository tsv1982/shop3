package com.tsv.shop3.Model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListModel implements IShoppingListModel {

    private List<ShoppingListItem> list;

    public ShoppingListModel() {
        list = new ArrayList<>();
        list.add(new ShoppingListItem(1, "max1", 100, 100, "null"));
        list.add(new ShoppingListItem(2, "max2", 100, 100, "null"));
        list.add(new ShoppingListItem(3, "max3", 100, 100, "null"));
    }

    @Override
    public boolean addItem(ShoppingListItem item) {
        for (ShoppingListItem itemBoolean : list) {
            if (itemBoolean.getId() == item.getId()) {
                return false;
            }
        }
        list.add(item);
        return true;
    }

    @Override
    public boolean removeItem(int id) {
        for (ShoppingListItem item : list) {
            if (item.getId() == id) {
                list.remove(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean changeItem(ShoppingListItem item) {
        for (ShoppingListItem itemBoolean : list) {
            if (item.getId() == itemBoolean.getId()) {
                list.remove(itemBoolean);
                list.add(item);
                return true;
            }
        }
        return false;
    }

    @Override
    public ShoppingListItem findItem(int id) {
        for (ShoppingListItem item : list) {
            if (item.getId() == id) {
                return item;
            }
        }                                       // может нужно вернуть null не знаю как лучше
        return new ShoppingListItem(000, "объект не найдет", 0, 0, "null");
    }

   @Override
    public List<ShoppingListItem> getItems() {
        return list;
    }
}
