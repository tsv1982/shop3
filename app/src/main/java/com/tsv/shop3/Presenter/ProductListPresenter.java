package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ProductItem;
import com.tsv.shop3.Model.IProductListItem;
import com.tsv.shop3.Model.ProductListItem;
import com.tsv.shop3.Util;
import com.tsv.shop3.View.IMainActivity;

import java.util.List;

public class ProductListPresenter implements IProductListPresenter {

    private IMainActivity iMainActivity;
    private IProductListItem iProductListItem;

    public ProductListPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        this.iProductListItem = new ProductListItem();
    }

    @Override
    public void onButtonGetItem(int id) {
        iMainActivity.showNameShoppingItem(iProductListItem.findItem(id).getName());
    }

    @Override
    public List<ProductItem> getListShoppingItem() {
        return iProductListItem.getItems(Util.DEFAULT_ITEMS);
    }

    @Override
    public void onButtonRemove() {

    }
}
