package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ShoppingItem;

import java.util.List;

public interface IShoppingListPresenter {

    void onButtonGetItem(int id);

    List<ShoppingItem> getListShoppingItem();

    void onButtonRemove();

}
