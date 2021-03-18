package com.quanlyquancafeapp.adapter.admin;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.quanlyquancafeapp.fragment.admin.product.CafeFragment;
import com.quanlyquancafeapp.fragment.admin.product.DrinkFragment;

public class ViewpagerAdapter extends FragmentStateAdapter {

    private Fragment fragmentCafe;
    private Fragment fragmentDrink;
    public ViewpagerAdapter(@NonNull Fragment fragment) {
        super(fragment);
        fragmentCafe = new CafeFragment();
        fragmentDrink = new DrinkFragment();
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return fragmentCafe;
            case 1:
                return fragmentDrink;
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
