package com.tsv.shop3.Presenter;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Room;

import com.tsv.shop3.Dao.AppDatabase;
import com.tsv.shop3.Dao.IproductDao;
import com.tsv.shop3.Dao.Util;
import com.tsv.shop3.Model.IproductModel;
import com.tsv.shop3.Model.ProductModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ShopPresenter implements IshopPresenter {

    @Override
    public List<IproductModel> getListProduct(Context context) {
        List<IproductModel> list = null;
        try {
            list = new AsyncTask<Void, Void, List<IproductModel>>() {
                @Override
                protected List<IproductModel> doInBackground(Void... voids) {
                    AppDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, Util.NAME_BD_PRODUCT_MODEL).build();
                    IproductDao iproductDao = db.productDao();
                    List<IproductModel> list1 = new ArrayList<>(iproductDao.getAll());
                    return list1;
                }
            }.execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void addProduct(String name, String price, String size, Context context) {
        IproductModel iproductModel = new ProductModel();
        iproductModel.setName(name);
        iproductModel.setPrice(Integer.parseInt(price));
        iproductModel.setSize(size);
        iproductModel.setID(new Random().nextInt());
        new AsyncTask<IproductModel, Void, Void>() {
            @Override
            protected Void doInBackground(IproductModel... iproductModels) {
                AppDatabase db = Room.databaseBuilder(context,
                        AppDatabase.class, Util.NAME_BD_PRODUCT_MODEL).build();
                IproductDao iproductDao = db.productDao();
                iproductDao.insert((ProductModel) iproductModel);
                return null;
            }
        }.execute();
    }

}
