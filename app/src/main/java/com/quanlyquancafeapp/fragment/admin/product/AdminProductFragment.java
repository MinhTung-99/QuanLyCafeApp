package com.quanlyquancafeapp.fragment.admin.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.google.android.material.tabs.TabLayoutMediator;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.admin.AdminProductAdapter;
import com.quanlyquancafeapp.adapter.admin.ViewpagerAdapter;
import com.quanlyquancafeapp.databinding.FragmentAdminProductBinding;
import com.quanlyquancafeapp.model.Product;
import com.quanlyquancafeapp.presenter.admin.product.AdminProductPresenter;

public class AdminProductFragment extends Fragment {
    private FragmentAdminProductBinding fragmentAdminProductBinding;
    private ViewpagerAdapter adapter;
    private AdminProductPresenter adminStorePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragmentAdminProductBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin_product, container, false);
        return fragmentAdminProductBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ViewpagerAdapter(this);
        fragmentAdminProductBinding.viewpager2.setAdapter(adapter);
        adminStorePresenter = new AdminProductPresenter(getContext());
        new TabLayoutMediator(fragmentAdminProductBinding.tabLayout, fragmentAdminProductBinding.viewpager2,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

        TextView tv = (TextView)(((LinearLayout)((LinearLayout) fragmentAdminProductBinding.tabLayout.getChildAt(0)).getChildAt(0)).getChildAt(1));
        tv.setText("Cafe");
        TextView tv1 = (TextView)(((LinearLayout)((LinearLayout) fragmentAdminProductBinding.tabLayout.getChildAt(0)).getChildAt(1)).getChildAt(1));
        tv1.setText("Drink");

        fragmentAdminProductBinding.imgAdd.setOnClickListener(v->{
            int positionTab = fragmentAdminProductBinding.tabLayout.getSelectedTabPosition();
            Bundle bundle = new Bundle();
            if(positionTab == 0){
                Log.d("KMFG", "CAFE");
                bundle.putString("species","CAFE");
            }else if(positionTab == 1) {
                Log.d("KMFG", "DRINK");
                bundle.putString("species","DRINK");
            }
            Navigation.findNavController(getView()).navigate(R.id.addOrUpdateStoreFragment, bundle);
        });
    }
}
