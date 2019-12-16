package com.tsv.shop3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tsv.shop3.Adapter.MainAdapter;
import com.tsv.shop3.CreateProductDialog;
import com.tsv.shop3.Presenter.IshopPresenter;
import com.tsv.shop3.Presenter.ShopPresenter;
import com.tsv.shop3.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    IshopPresenter ishopPresenter;

    @BindView(R.id.LV_ShopLv)
    ListView listView;

    @OnClick({R.id.fab, R.id.fab_add, R.id.fab_delete})
    void onSaveClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                break;
            case R.id.fab_add:
                CreateProductDialog createProductDialog = new CreateProductDialog();
                createProductDialog.show(getSupportFragmentManager(), "create dialog");
                break;
            case R.id.fab_delete:
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        ishopPresenter = new ShopPresenter();

        MainAdapter mainAdapter = new MainAdapter(this, R.layout.list_main, ishopPresenter.getListProduct(this));
        listView.setAdapter(mainAdapter);   // сетаем адаптер в листвиев


    }


}
