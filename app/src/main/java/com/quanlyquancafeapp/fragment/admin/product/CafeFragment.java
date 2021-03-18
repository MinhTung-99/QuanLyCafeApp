package com.quanlyquancafeapp.fragment.admin.product;

import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminProductAdapter;
import com.quanlyquancafeapp.databinding.DialogDeleteProductBinding;
import com.quanlyquancafeapp.databinding.FragmentCafeBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.presenter.admin.product.AdminCafePresenter;
import com.quanlyquancafeapp.view.admin.IAdminCafeView;

import java.util.ArrayList;

public class CafeFragment extends Fragment implements AdminProductAdapter.RecyclerViewItemOnclick, IAdminCafeView {
    private FragmentCafeBinding binding;
    private ArrayList<Product> productCafe;
    private AdminProductAdapter adapter;
    private AdminCafePresenter cafePresenter;
    private DialogDeleteProductBinding dialogDeleteProductBinding;
    private AlertDialog alertDialogDelete;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogDeleteProductBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_product, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cafe, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDialogDelete();
        cafePresenter = new AdminCafePresenter(getContext());
        ArrayList<Product> products = cafePresenter.getProducts();
        productCafe = new ArrayList<>();
        for(Product product: products){
            if(product.getSpecies().equals("CAFE")){
                Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
                product.setBitmap(bitmap);
                productCafe.add(product);
            }
        }
        adapter = new AdminProductAdapter(productCafe, this);
        binding.rvCafe.setAdapter(adapter);
        adapter.updateProduct(productCafe);

        dialogDeleteProductBinding.btnCancel.setOnClickListener(v->{
            alertDialogDelete.dismiss();
        });
    }
    @Override
    public void btnUpdate(Product product) {
        Toast.makeText(getContext(), "prduct", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void btnDelete() {
        alertDialogDelete.show();
        dialogDeleteProductBinding.btnYes.setOnClickListener(v->{

        });
    }

    @Override
    public void initDialogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteProductBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
        alertDialogDelete.setCancelable(false);
    }
}
