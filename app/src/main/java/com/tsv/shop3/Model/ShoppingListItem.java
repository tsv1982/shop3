package com.tsv.shop3.Model;

import com.tsv.shop3.BD.BD;
import com.tsv.shop3.Model.Entity.ShoppingItem;

import java.util.List;

public class ShoppingListItem implements IShoppingListItem {

    BD bd;

    public ShoppingListItem() {
        bd = BD.getInstance();
    }

    @Override
    public boolean addItem(ShoppingItem shoppingItem) {
        return bd.addToBd(shoppingItem);
    }

    @Override
    public ShoppingItem findItem(int id) {
        if (BD.getInstance().getById(id) != null) {
            return bd.getById(id);
        } else {
            return null;
        }
    }

    @Override
    public boolean removeItem(int id) {
        return bd.remove(id);
    }

    @Override
    public List<ShoppingItem> getListShoppingItem() {
        return bd.getList();
    }
}
