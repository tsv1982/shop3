package com.tsv.shop3.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tsv.shop3.DB.DBuser;
import com.tsv.shop3.Entity.Product;
import com.tsv.shop3.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

//1
public class MainAdapter extends ArrayAdapter<Product> {

    private LayoutInflater inflater;
    private int layout;
    private List<Product> list;
    private SharedPreferences sharedPreferences;

    public MainAdapter(Context context, int resource, List<Product> list) {
        super(context, resource, list);
        this.list = list;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
        sharedPreferences = context.getSharedPreferences("telNumber2", MODE_PRIVATE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(this.layout, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        if (list.get(position).isMarked()) {

            if (sharedPreferences.getString("telNumber2", "!").equals(DBuser.getInstance().getUserList().get(0).getTelNumber())) {
                holder.textViewNameItem.setText(Html.fromHtml("<font color='#D81B60'>" + list.get(position).getName() + "</font>"));
            } else {
                holder.textViewNameItem.setText(Html.fromHtml("<font color='#D81B60'>" + list.get(position).getName() + "<br>" + "в корзине" + "</font>"));
            }
            holder.textViewSizeItem.setText(Html.fromHtml("<font color='#D81B60'>" + list.get(position).getSizeMarked() + "</font>"));
            holder.textViewPrice.setText(Html.fromHtml("<font color='#D81B60'>" + list.get(position).getPrice() + "</font>"));
            holder.tMain_razmer.setText(Html.fromHtml("<font color='#D81B60'>размер:</font>"));
            holder.tMain_cena.setText(Html.fromHtml("<font color='#D81B60'>ена:</font>"));
            holder.imageShopVie.setImageAlpha(120);
        } else {
            holder.textViewNameItem.setText(list.get(position).getName());
            holder.textViewSizeItem.setText(String.valueOf(list.get(position).getSizeMarked()));
            holder.textViewPrice.setText(String.valueOf(list.get(position).getPrice()));
            holder.tMain_razmer.setText("размер:");
            holder.tMain_cena.setText("цена:");

        }



        Picasso.with(view.getContext())
                .load(list.get(position).getUrlPictureByFireBase())
                .placeholder(R.drawable.loading)   // заглушка во время загрузки
                .error(R.drawable.loading_error)  // если еррор
                .into(holder.imageShopVie);

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.IV_shopping_item)
        ImageView imageShopVie;
        @BindView(R.id.TV_name_shopping_item)
        TextView textViewNameItem;
        @BindView(R.id.TV_size_shopping_item)
        TextView textViewSizeItem;
        @BindView(R.id.TV_price_shopping_item)
        TextView textViewPrice;
        @BindView(R.id.main_razmer)
        TextView tMain_razmer;
        @BindView(R.id.main_cena)
        TextView tMain_cena;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

