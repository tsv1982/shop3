package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ProductItem;
import com.tsv.shop3.Model.IProductItemsModel;
import com.tsv.shop3.Model.ProductItemsModel;
import com.tsv.shop3.Util;
import com.tsv.shop3.View.IMainActivity;

import java.util.List;

public class ProductListPresenter implements IProductListPresenter {

    private IMainActivity iMainActivity;
    private IProductItemsModel iProductItemsModel;

    public ProductListPresenter(IMainActivity iMainActivity) {
        this.iMainActivity = iMainActivity;
        this.iProductItemsModel = new ProductItemsModel();
    }

    @Override
    public void onButtonGetItem(int id) {
        iMainActivity.showNameShoppingItem(iProductItemsModel.findItem(id).getName());
    }

    @Override
    public List<ProductItem> getListShoppingItem() {
        return iProductItemsModel.getItems(Util.DEFAULT_ITEMS);
    }

    @Override
    public void onButtonRemove() {

    }
}
