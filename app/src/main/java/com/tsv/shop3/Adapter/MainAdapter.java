package com.tsv.shop3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tsv.shop3.Model.IproductModel;
import com.tsv.shop3.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainAdapter extends ArrayAdapter<IproductModel> {

    private LayoutInflater inflater;
    private int layout;
    private List<IproductModel> product1List;

    public MainAdapter(Context context, int resource, List<IproductModel> product1List) {
        super(context, resource, product1List);
        this.product1List = product1List;
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

        holder.nameView.setText(product1List.get(position).getName());
        holder.sizeView.setText(product1List.get(position).getSize());
        holder.priceView.setText(String.valueOf(product1List.get(position).getPrice()));

        return view;
    }

    static class ViewHolder {

        @BindView(R.id.imageIV)
        ImageView imageShopVie;
        @BindView(R.id.nameIV)
        TextView nameView;
        @BindView(R.id.sizeIV)
        TextView sizeView;
        @BindView(R.id.priceIV)
        TextView priceView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

