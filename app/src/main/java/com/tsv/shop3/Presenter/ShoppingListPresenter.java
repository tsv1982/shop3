package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.Entity.ShoppingItem;
import com.tsv.shop3.Model.IShoppingListItem;
import com.tsv.shop3.Model.ShoppingListItem;
import com.tsv.shop3.View.IMainActivity;

import java.util.List;

public class ShoppingListPresenter implements IShoppingListPresenter {

    private IMainActivity iMainActivity;
    private IShoppingListItem iShoppingListItem;

    public ShoppingListPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        this.iShoppingListItem = new ShoppingListItem();
    }

    @Override
    public void onButtonGetItem(int id) {
        iMainActivity.showNameShoppingItem(iShoppingListItem.findItem(id).getName());
    }

    @Override
    public List<ShoppingItem> getListShoppingItem() {
        return iShoppingListItem.getListShoppingItem();
    }


    @Override
    public void onButtonRemove() {

    }
}
