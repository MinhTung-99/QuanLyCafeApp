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
import androidx.navigation.Navigation;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminProductAdapter;
import com.quanlyquancafeapp.databinding.DialogDeleteProductBinding;
import com.quanlyquancafeapp.databinding.FragmentDrinkBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.presenter.admin.product.AdminDrinkPresenter;
import com.quanlyquancafeapp.view.admin.IAdminCafeView;
import com.quanlyquancafeapp.view.admin.IAdminDrinkView;

import java.util.ArrayList;

public class DrinkFragment extends Fragment implements AdminProductAdapter.RecyclerViewItemOnclick, IAdminDrinkView {
    private FragmentDrinkBinding binding;
    private ArrayList<Product> productDrink;
    private AdminProductAdapter adapter;
    private AdminDrinkPresenter drinkPresenter;
    private DialogDeleteProductBinding dialogDeleteProductBinding;
    private AlertDialog alertDialogDelete;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        dialogDeleteProductBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_delete_product, container, false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_drink, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDiaLogDelete();
        drinkPresenter = new AdminDrinkPresenter(getContext());
        productDrink = drinkPresenter.getDrinkProducts();
        adapter = new AdminProductAdapter(productDrink, this);
        binding.rvDrink.setAdapter(adapter);
    }
    @Override
    public void btnUpdate(Product product) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("product", product);
        Navigation.findNavController(getView()).navigate(R.id.addOrUpdateProductFragment, bundle);
    }
    @Override
    public void btnDelete(Product product) {
        alertDialogDelete.show();
        dialogDeleteProductBinding.btnYes.setOnClickListener(v->{
            drinkPresenter.deleteProduct(product.getId());
            productDrink = drinkPresenter.getDrinkProducts();
            adapter.updateProduct(productDrink);
            binding.rvDrink.setAdapter(adapter);
            alertDialogDelete.dismiss();
        });
    }
    @Override
    public void onLongClick(Product product) {
        AdminProductBottomSheetFragment adminProductBottomSheetFragment = new AdminProductBottomSheetFragment(product);
        adminProductBottomSheetFragment.show(getChildFragmentManager(), adminProductBottomSheetFragment.getTag());
    }
    @Override
    public void initDiaLogDelete() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        dialogBuilder.setView(dialogDeleteProductBinding.getRoot());
        alertDialogDelete = dialogBuilder.create();
        alertDialogDelete.setCancelable(false);
    }
}
