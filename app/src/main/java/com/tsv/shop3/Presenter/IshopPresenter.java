package com.tsv.shop3.Presenter;

import android.content.Context;

import com.tsv.shop3.Model.IproductModel;

import java.util.List;

public interface IshopPresenter {

    List<IproductModel> getListProduct(Context context);
    void addProduct(String name, String price, String size, Context context);
}
