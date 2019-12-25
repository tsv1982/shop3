package com.tsv.shop3.Model;

import java.util.List;

public interface IProductItemsModel {

    boolean addItem(ProductItem item);

    boolean removeItem(int id);

    boolean changeItem(ProductItem item);

    ProductItem findItem(int id);

    List<ProductItem> getItems(int kind);
}
