package com.tsv.shop3.Dao;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.tsv.shop3.Model.ProductModel;

@Database(entities = {ProductModel.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IproductDao productDao();

}
