package com.tsv.shop3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsv.shop3.Model.Entity.ShoppingItem;
import com.tsv.shop3.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends ArrayAdapter<ShoppingItem> {

    private LayoutInflater inflater;
    private int layout;
    private List<ShoppingItem> list;

    public MainAdapter(Context context, int resource, List<ShoppingItem> list) {
        super(context, resource, list);
        this.list = list;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
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

        holder.textViewNameItem.setText(list.get(position).getName());


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

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

