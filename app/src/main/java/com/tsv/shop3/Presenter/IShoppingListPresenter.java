package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ShoppingListItem;

import java.util.List;

public interface IShoppingListPresenter {

    void onButtonGetItem(int id);

    List<ShoppingListItem> getListShoppingItem();

    void onButtonRemove();

}
