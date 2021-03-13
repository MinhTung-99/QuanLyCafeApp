package com.quanlyquancafeapp.fragment;

import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.ViewpagerAdapter;
import com.quanlyquancafeapp.databinding.FragmentStoreBinding;
import com.quanlyquancafeapp.presenter.StorePresenter;

public class StoreFragment extends Fragment {
    private FragmentStoreBinding binding;
    private ViewpagerAdapter adapter;
    private StorePresenter storePresenter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_store, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new ViewpagerAdapter(this);
        binding.viewpager2.setAdapter(adapter);
        storePresenter = new StorePresenter(getContext());
        new TabLayoutMediator(binding.tabLayout, binding.viewpager2,
                (tab, position) -> tab.setText("OBJECT " + (position + 1))
        ).attach();

        TextView tv = (TextView)(((LinearLayout)((LinearLayout)binding.tabLayout.getChildAt(0)).getChildAt(0)).getChildAt(1));
        tv.setText("Cafe");
        TextView tv1 = (TextView)(((LinearLayout)((LinearLayout)binding.tabLayout.getChildAt(0)).getChildAt(1)).getChildAt(1));
        tv1.setText("Drink");

        binding.imgAdd.setOnClickListener(v->{
            int positionTab = binding.tabLayout.getSelectedTabPosition();
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
