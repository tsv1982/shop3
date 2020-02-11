package com.tsv.shop3.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.tsv.shop3.Adapter.MainAdapter;
import com.tsv.shop3.DB.DBproduct;
import com.tsv.shop3.DB.DBuser;
import com.tsv.shop3.Entity.Product;
import com.tsv.shop3.R;
import com.tsv.shop3.Util;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity implements IActivity {

    @BindView(R.id.LV_Shopping_Item)
    ListView listView;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.fab_exit)
    FloatingActionButton fabExit;
    @BindView(R.id.fab_sort)
    FloatingActionButton fabDelete;
    @BindView(R.id.fab_delete_admin)
    FloatingActionButton fabDeleteAdmin;
    @BindView(R.id.fab_basket)
    FloatingActionButton fabAdd;
    @BindView(R.id.fab_add_admin)
    FloatingActionButton fabAddAdmin;
    @BindView(R.id.fab_change_admin)
    FloatingActionButton fabChangeAdmin;
    @BindView(R.id.fab_orders_admin)
    FloatingActionButton fabOrdersAdmin;
    @BindView(R.id.fab_archive_orders_admin)
    FloatingActionButton fabAchiveOrdersAdmin;
    @BindView(R.id.TV_exit)
    TextView textViewExit;
    @BindView(R.id.TV_delete)
    TextView textViewSort;
    @BindView(R.id.TV_delete_admin)
    TextView textViewDeleteAdmin;
    @BindView(R.id.TV_add)
    TextView textViewBasket;
    @BindView(R.id.TV_add_admin)
    TextView textViewAddAdmin;
    @BindView(R.id.TV_change_admin)
    TextView textViewChangeAdmin;
    @BindView(R.id.TV_orders_admin)
    TextView textViewOrdersAdmin;
    @BindView(R.id.TV_archive_orders_admin)
    TextView textViewArchiveOrdersAdmin;
    @BindView(R.id.TV_count_tw)
    TextView textViewCount;

    private MainAdapter mainAdapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean showFloatBtn = true;
    private boolean marked = true;
    private int checkedItem = 0; // cow


    @OnClick({R.id.fab, R.id.fab_exit, R.id.fab_basket, R.id.fab_sort, R.id.fab_add_admin, R.id.fab_delete_admin,
            R.id.fab_change_admin, R.id.fab_orders_admin, R.id.fab_archive_orders_admin})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab: {
                if (showFloatBtn) {
                    showFloatActionButton(2);
                    showFloatBtn = false;
                } else {
                    showFloatActionButton(1);
                    showFloatBtn = true;
                }
                break;
            }
            case R.id.fab_exit: {
                editor = sharedPreferences.edit();
                editor.putString("telNumber2", "!");
                editor.apply();
                userIdentification(this);

                for (Product p : DBproduct.getInstance().getProductList(Util.MARKED_ITEMS)) {
                    p.setMarked(false);
                    p.setSizeMarked("не выбранное");
                    DBproduct.getInstance().changeInFireBase(p);
                }
                break;
            }
            case R.id.fab_basket: {
                Intent intent = new Intent(this, Basket.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.fab_sort: {
                sortAlert();
                break;
            }
            case R.id.fab_add_admin: {
                sharedPreferences = this.getSharedPreferences("telNumber2", MODE_PRIVATE);
                editor = sharedPreferences.edit();
                editor.putInt("position", DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS).size());
                editor.apply();

                Intent intent = new Intent(this, AddProduct.class);
                startActivity(intent);
                finish();

                break;
            }
            case R.id.fab_delete_admin: {
                if (!marked) {
                    for (Product p : DBproduct.getInstance().getProductList(Util.MARKED_ITEMS)) {
                        DBproduct.getInstance().deleteFromFireBase(p);
                        adapterNotify(sharedPreferences.getInt("position", 0));
                        Toast.makeText(this, "элемент удален", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "нет выбранных элементов", Toast.LENGTH_SHORT).show();
                }

                break;
            }
            case R.id.fab_change_admin: {
                if (!marked) {
                    Intent intent = new Intent(this, СhangeProduct.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(this, "выберите элемент для изменения", Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.fab_orders_admin: {
                Intent intent = new Intent(this, MenedgerActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.fab_archive_orders_admin: {
                Intent intent = new Intent(this, ListTelUserActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DBproduct.getInstance().setActivity(this);
        adminOrUser();
        showFloatActionButton(1);

        mainAdapter = new MainAdapter(this, R.layout.list_main, DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS));
        listView.setAdapter(mainAdapter);

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                if (scrollState > 0) {
                    showFloatActionButton(3);
                    showFloatBtn = true;
                } else {
                    showFloatActionButton(4);
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        listView.setOnItemClickListener((parent, v, position, id) -> {

            editor = sharedPreferences.edit();
            editor.putInt("position", position);
            editor.apply();

            showFloatActionButton(1);

            markProduct(position);
        });

    }

    boolean adminOrUser() {
        sharedPreferences = this.getSharedPreferences("telNumber2", MODE_PRIVATE);

        if (sharedPreferences.getString("telNumber2", "!").equals("!")) {
            userIdentification(this);
        }

        if (sharedPreferences.getString("telNumber2", "!").equals(DBuser.getInstance().getUserList().get(0).getTelNumber())) {
            return true;
        } else {
            return false;
        }
    }

    void markProduct(int position) {

        if (marked) {

            if (!adminOrUser()) {
                String[] productSize = DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS).get(position).getSize().trim().split("//");

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("выбрать размер");

                builder.setSingleChoiceItems(productSize, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        checkedItem = which;
                    }
                });

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Product product = DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS).get(position);
                        product.setMarked(true);
                        product.setSizeMarked(productSize[checkedItem]);
                        DBproduct.getInstance().changeInFireBase(product);
                        textViewCount.setText(String.valueOf(countProduct()));
                        adapterNotify(position);
                        Toast toast = Toast.makeText(getApplicationContext(), product.getName() + "\n" + "добавлено в корзину", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
                builder.setNegativeButton("Cancel", null);

                AlertDialog dialog = builder.create();
                dialog.show();

            } else {
                Product product = DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS).get(position);
                product.setMarked(true);
                DBproduct.getInstance().changeInFireBase(product);
                textViewCount.setText(String.valueOf(countProduct()));                  // если админ отчечаем без размера
                adapterNotify(position);
            }
            marked = false;

        } else {
            Product product = DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS).get(position);
            product.setMarked(false);
            product.setSizeMarked("не выбранное");
            DBproduct.getInstance().changeInFireBase(product);

            textViewCount.setText(String.valueOf(countProduct()));
            adapterNotify(position);

            marked = true;
        }
    }

    void adapterNotify(int position) {
        mainAdapter = null;
        mainAdapter = new MainAdapter(this, R.layout.list_main, DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS));
        mainAdapter.notifyDataSetChanged();
        listView.setAdapter(mainAdapter);
        listView.setSelection(position);
    }

    int countProduct() {
        int i = 0;
        for (Product p : DBproduct.getInstance().getProductList(Util.DEFAULT_ITEMS)) {
            if (p.isMarked()) {
                i++;
            }
        }
        return i;
    }

    public void showFloatActionButton(int a) {

        switch (a) {
            case 1: {
                fabExit.hide();
                fabDelete.hide();
                fabAdd.hide();
                textViewExit.setVisibility(View.INVISIBLE);
                textViewSort.setVisibility(View.INVISIBLE);
                textViewBasket.setVisibility(View.INVISIBLE);

                if (adminOrUser()) {
                    textViewCount.setVisibility(View.INVISIBLE);
                }

                fabAddAdmin.hide();
                textViewAddAdmin.setVisibility(View.INVISIBLE);
                fabDeleteAdmin.hide();
                textViewDeleteAdmin.setVisibility(View.INVISIBLE);
                fabChangeAdmin.hide();
                textViewChangeAdmin.setVisibility(View.INVISIBLE);
                fabOrdersAdmin.hide();
                textViewOrdersAdmin.setVisibility(View.INVISIBLE);
                fabAchiveOrdersAdmin.hide();
                textViewArchiveOrdersAdmin.setVisibility(View.INVISIBLE);

                break;
            }
            case 2: {
                if (adminOrUser()) {
                    fabExit.show();
                    textViewExit.setVisibility(View.VISIBLE);
                    fabAddAdmin.show();
                    textViewAddAdmin.setVisibility(View.VISIBLE);
                    fabDeleteAdmin.show();
                    textViewDeleteAdmin.setVisibility(View.VISIBLE);
                    fabChangeAdmin.show();
                    textViewChangeAdmin.setVisibility(View.VISIBLE);
                    fabOrdersAdmin.show();
                    textViewOrdersAdmin.setVisibility(View.VISIBLE);
                    fabAchiveOrdersAdmin.show();
                    textViewArchiveOrdersAdmin.setVisibility(View.VISIBLE);
                } else {
                    fabExit.show();
                    textViewExit.setVisibility(View.VISIBLE);

                    fabDelete.show();
                    textViewSort.setVisibility(View.VISIBLE);
                    textViewSort.setText("сортировать");
                    fabDelete.setImageResource(R.drawable.check);

                    fabAdd.show();
                    textViewBasket.setVisibility(View.VISIBLE);
                    textViewBasket.setText("корзина");
                    fabAdd.setImageResource(R.drawable.korzina_black);
                }
                break;

            }
            case 3: {
                fab.hide();
                fabExit.hide();
                fabDelete.hide();
                fabAdd.hide();
                textViewExit.setVisibility(View.INVISIBLE);
                textViewSort.setVisibility(View.INVISIBLE);
                textViewBasket.setVisibility(View.INVISIBLE);
                textViewCount.setVisibility(View.INVISIBLE);

                fabAddAdmin.hide();
                textViewAddAdmin.setVisibility(View.INVISIBLE);
                fabDeleteAdmin.hide();
                textViewDeleteAdmin.setVisibility(View.INVISIBLE);
                fabChangeAdmin.hide();
                textViewChangeAdmin.setVisibility(View.INVISIBLE);
                fabOrdersAdmin.hide();
                textViewOrdersAdmin.setVisibility(View.INVISIBLE);
                fabAchiveOrdersAdmin.hide();
                textViewArchiveOrdersAdmin.setVisibility(View.INVISIBLE);
                break;
            }
            case 4: {
                fab.show();
                if (adminOrUser()) {
                    textViewCount.setVisibility(View.INVISIBLE);
                } else {
                    textViewCount.setVisibility(View.VISIBLE);
                }
            }
        }


    }

    public void userIdentification(Context context) {

        AlertDialog.Builder ad = new AlertDialog.Builder(this, AlertDialog.THEME_TRADITIONAL);
        ad.setTitle("               введите номер для \n                  индентификации  ");  // заголовок
        ad.setMessage("в формате  +38 (0xx)xxx-xx-xx"); // сообщение

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        ad.setView(linLayout);

        EditText eTTelNumber = new EditText(this);
        eTTelNumber.setText("+38(0");
        eTTelNumber.setTextColor(Color.WHITE);
        eTTelNumber.requestFocus();

        EditText eTNameUser = new EditText(this);
        eTNameUser.setText("имя");
        eTNameUser.setTextColor(Color.WHITE);

        linLayout.addView(eTTelNumber);
        linLayout.addView(eTNameUser);

        eTNameUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTNameUser.setText("");
            }
        });

        ad.setPositiveButton("вход", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                editor = sharedPreferences.edit();
                editor.putString("telNumber2", String.valueOf(eTTelNumber.getText()));
                editor.apply();


                editor.putString("nameUser", String.valueOf(eTNameUser.getText()));
                editor.apply();

                Intent intent11 = new Intent(context, MainActivity.class);
                startActivity(intent11);
                finish();

                Toast toast = Toast.makeText(getApplicationContext(), "добро пожаловать \n " + eTNameUser.getText(), Toast.LENGTH_SHORT);
                toast.show();

                dialog.cancel();

            }
        });
        ad.setCancelable(true);
        ad.show();
    }

    public void sortAlert() {
        AlertDialog.Builder ad = new AlertDialog.Builder(this, AlertDialog.THEME_TRADITIONAL);

        LinearLayout linLayout = new LinearLayout(this);
        linLayout.setOrientation(LinearLayout.VERTICAL);
        ad.setView(linLayout);

        Button btnNameSort = new Button(ad.getContext());
        btnNameSort.setText("             сортировать по имени              ");
        btnNameSort.setBackgroundColor(this.getResources().getColor(R.color.colorBT));
        btnNameSort.setTextSize(20);

        Button btnPriceSort = new Button(ad.getContext());
        btnPriceSort.setText("            сортировать по цене            ");
        btnPriceSort.setBackgroundColor(this.getResources().getColor(R.color.colorBT));
        btnPriceSort.setTextSize(20);

        btnNameSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnNameSort.setBackgroundColor(v.getResources().getColor(R.color.colorYellow));

                btnPriceSort.setBackgroundColor(v.getResources().getColor(R.color.colorBT));
            }
        });

        btnPriceSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPriceSort.setBackgroundColor(v.getResources().getColor(R.color.colorYellow));

                btnNameSort.setBackgroundColor(v.getResources().getColor(R.color.colorBT));
            }
        });

        linLayout.addView(btnNameSort);
        linLayout.addView(btnPriceSort);

        ad.setPositiveButton("применить", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                adapterNotify(0);
                dialog.cancel();
            }
        });

        ad.setCancelable(true);
        ad.show();
    }

    @Override
    public void notifyBD() {
        adapterNotify(0);
    }

    void setMarkedFalse() {
        for (Product p : DBproduct.getInstance().getProductList(Util.MARKED_ITEMS)) {
            p.setMarked(false);
            p.setSizeMarked("не выбранное");
            DBproduct.getInstance().changeInFireBase(p);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setMarkedFalse();
        countProduct();
        showFloatActionButton(1);

    }


}
