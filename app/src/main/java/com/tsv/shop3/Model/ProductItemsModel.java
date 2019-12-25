package com.tsv.shop3.Model;

import com.tsv.shop3.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductItemsModel implements IProductItemsModel {

    List<ProductItem> items;

    public ProductItemsModel() {
        items = new ArrayList<>();
    }

    @Override
    public boolean addItem(ProductItem item) {
        if (findItem(item.getId()) != null) {
            items.add(item);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeItem(int id) {
        return items.remove(findItem(id));
    }

    @Override
    public boolean changeItem(ProductItem item) {
        try {
            items.set(items.indexOf(findItem(item.getId())), item);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ProductItem findItem(int id) {
        for (ProductItem item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    @Override
    public List<ProductItem> getItems(int kind) {
        switch (kind) {
            case Util.SORT_BY_NAME: {
                Collections.sort(items, new Comparator<ProductItem>() {
                    public int compare(ProductItem item1, ProductItem item2) {
                        return item1.getName().compareTo(item2.getName());
                    }
                });
            }
            case Util.SORT_BY_PRICE: {
                Collections.sort(items, new Comparator<ProductItem>() {
                    public int compare(ProductItem item1, ProductItem item2) {
                        return item1.getId() - item2.getId();
                    }
                });
            }
            case Util.MARKED_ITEMS: {
                List<ProductItem> list = new ArrayList<>();
                for (ProductItem item : items) {
                    if (item.isMarked()) {
                        list.add(item);
                    }
                }
                return list;
            }
            default: {
                return items;
            }
        }

    }


}
