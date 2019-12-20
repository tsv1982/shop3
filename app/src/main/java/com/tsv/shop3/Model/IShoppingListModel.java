package com.tsv.shop3.Model;

import java.util.List;

public interface IShoppingListModel {

    boolean addItem(ShoppingListItem item);

    boolean removeItem(int id);

    boolean changeItem(ShoppingListItem item);

    ShoppingListItem findItem(int id);

    List<ShoppingListItem> getItems();
}
