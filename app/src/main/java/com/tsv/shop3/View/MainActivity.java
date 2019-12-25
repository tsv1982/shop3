package com.tsv.shop3.View;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.tsv.shop3.Adapter.MainAdapter;
import com.tsv.shop3.Presenter.IProductListPresenter;
import com.tsv.shop3.Presenter.ProductListPresenter;
import com.tsv.shop3.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {

    IProductListPresenter iProductListPresenter;

    @BindView(R.id.LV_Shopping_Item)
    ListView listView;
    @BindView(R.id.TV_Text_Name)
    TextView textView;
    @BindView(R.id.ET_id_shopping_item)
    EditText editText;

    @OnClick({R.id.fab, R.id.fab_add, R.id.fab_delete, R.id.btn_get_item})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                break;
            case R.id.fab_add:
                break;
            case R.id.fab_delete:
                break;
            case R.id.btn_get_item:
                iProductListPresenter.onButtonGetItem(Integer.parseInt(String.valueOf(editText.getText())));
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        iProductListPresenter = new ProductListPresenter(this);

        MainAdapter mainAdapter = new MainAdapter(this, R.layout.list_main, iProductListPresenter.getListShoppingItem());
        listView.setAdapter(mainAdapter);

    }

    @Override
    public void showNameShoppingItem(String name) {
        textView.setText(name);
    }


}
