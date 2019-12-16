package com.tsv.shop3.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.tsv.shop3.Model.IproductModel;
import com.tsv.shop3.Model.ProductModel;

import java.util.List;

@Dao
public interface IproductDao {
    @Query("SELECT * FROM ProductModel")
    List<ProductModel> getAll();

    @Query("SELECT * FROM ProductModel WHERE id = :id")
    ProductModel getById(long id);

    @Insert
    void insert(ProductModel productModel);

    @Update
    void update(ProductModel productModel);

    @Delete
    void delete(ProductModel productModel);
}
