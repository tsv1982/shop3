package com.tsv.shop3;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.tsv.shop3.Presenter.ShopPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CreateProductDialog extends AppCompatDialogFragment {

    @BindView(R.id.et_name)
    EditText editTextName;
    @BindView(R.id.et_price)
    EditText editTextPrice;
    @BindView(R.id.et_size)
    EditText editTextSize;


    @OnClick(R.id.btn_save)
    public void submit() {

        new ShopPresenter().addProduct(String.valueOf(editTextName.getText()),
                String.valueOf(editTextPrice.getText()),
                String.valueOf(editTextSize.getText()), getContext());
        Toast.makeText(getContext(), "сохранено", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);


        ButterKnife.bind(this, view);

        builder.setView(view);
        builder.setTitle("Add New Product");
        builder.setCancelable(true);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

    }

}
