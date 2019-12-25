package com.tsv.shop3.Presenter;

import com.tsv.shop3.Model.ProductItem;

import java.util.List;

public interface IProductListPresenter {

    void onButtonGetItem(int id);

    List<ProductItem> getListShoppingItem();

    void onButtonRemove();

}
