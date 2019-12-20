package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ShoppingListItem;
import com.tsv.shop3.Model.IShoppingListModel;
import com.tsv.shop3.Model.ShoppingListModel;
import com.tsv.shop3.View.IMainActivity;

import java.util.List;

public class ShoppingListPresenter implements IShoppingListPresenter {

    private IMainActivity iMainActivity;
    private IShoppingListModel iShoppingListModel;

    public ShoppingListPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        this.iShoppingListModel = new ShoppingListModel();
    }

    @Override
    public void onButtonGetItem(int id) {
        iMainActivity.showNameShoppingItem(iShoppingListModel.findItem(id).getName());
    }

    @Override
    public List<ShoppingListItem> getListShoppingItem() {
        return iShoppingListModel.getItems();
    }


    @Override
    public void onButtonRemove() {

    }
}
