package com.quanlyquancafeapp.fragment.admin.product;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

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
        productCafe = cafePresenter.getCafeProducts();
        setCafeAdapter();
        dialogDeleteProductBinding.btnCancel.setOnClickListener(v->{
            alertDialogDelete.dismiss();
        });
    }
    private void setCafeAdapter(){
        adapter = new AdminProductAdapter(productCafe, this);
        binding.rvCafe.setAdapter(adapter);
    }
    @Override
    public void btnUpdate(Product product) {
        Bundle bundle = new Bundle();
        product.setAdd(false);
        bundle.putSerializable("product", product);
        Navigation.findNavController(getView()).navigate(R.id.addOrUpdateProductFragment, bundle);
    }
    @Override
    public void btnDelete(Product product) {
        alertDialogDelete.show();
        dialogDeleteProductBinding.btnYes.setOnClickListener(v->{
            cafePresenter.deleteProduct(product.getId());
            productCafe = cafePresenter.getCafeProducts();
            adapter.updateProduct(productCafe);
            binding.rvCafe.setAdapter(adapter);
            alertDialogDelete.dismiss();
        });
    }

    @Override
    public void onLongClick(Product product) {
        AdminProductBottomSheetFragment adminProductBottomSheetFragment = new AdminProductBottomSheetFragment(product);
        adminProductBottomSheetFragment.show(getChildFragmentManager(), adminProductBottomSheetFragment.getTag());
    }

    @Override
    public void initDialogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteProductBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
        alertDialogDelete.setCancelable(false);
    }
}
