package com.quanlyquancafeapp.fragment.admin.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.databinding.FragmentAdminProductBottomSheetBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.utils.PriceUtil;

public class AdminProductBottomSheetFragment extends BottomSheetDialogFragment {
    private FragmentAdminProductBottomSheetBinding adminProductBottomSheetBinding;
    private Product product;
    public AdminProductBottomSheetFragment(Product product) {
        this.product = product;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        adminProductBottomSheetBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_product_bottom_sheet, container, false);
        return adminProductBottomSheetBinding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adminProductBottomSheetBinding.imgProduct.setImageBitmap(product.getImageBitmap());
        adminProductBottomSheetBinding.txtName.setText("Tên sản phẩm: " + product.getName());
        adminProductBottomSheetBinding.txtUnit.setText("Đơn vị: " + product.getUnit());
        String setupMoney = PriceUtil.setupPrice(String.valueOf(product.getPrice()));
        adminProductBottomSheetBinding.txtPrice.setText("Giá: " + setupMoney + " VND");
        adminProductBottomSheetBinding.txtSale.setText("Khuyến mãi: " + product.getSale() + "%");
        adminProductBottomSheetBinding.txtAvailableQuantity.setText("Số lượng: " + product.getAvailableQuantity() + " " + product.getUnit());
        adminProductBottomSheetBinding.txtBarcode.setText("Mã vạch: " + product.getBarcode());
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        view.post(() -> {
            View parent = (View) view.getParent();
            CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) (parent).getLayoutParams();
            CoordinatorLayout.Behavior behavior = params.getBehavior();
            BottomSheetBehavior bottomSheetBehavior = (BottomSheetBehavior) behavior;
            bottomSheetBehavior.setPeekHeight(2300);
            bottomSheetBehavior.setHideable(false);
        });
    }
}
