package com.tsv.shop3.Model;

import com.tsv.shop3.Model.Entity.ShoppingItem;

import java.util.List;

public interface IShoppingListItem {

    boolean addItem(ShoppingItem shoppingItem);

    ShoppingItem findItem(int id);

    boolean removeItem(int id);

    List<ShoppingItem> getListShoppingItem();
}
