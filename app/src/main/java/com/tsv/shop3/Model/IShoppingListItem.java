package com.tsv.shop3.Model;

import java.util.List;

public interface IShoppingListItem {

    boolean addItem(ShoppingItem shoppingItem);



    ShoppingItem findItem(int id);
    ShoppingItem findItem1(int id);

    boolean removeItem(int id);

    List<ShoppingItem> getListShoppingItem();
}
