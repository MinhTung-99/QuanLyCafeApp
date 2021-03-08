package com.quanlyquancafeapp.fragment;

import android.graphics.Color;
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
import androidx.recyclerview.widget.GridLayoutManager;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.AdminAdapter;
import com.quanlyquancafeapp.databinding.FragmentAdminBinding;
import com.quanlyquancafeapp.model.Admin;
import com.quanlyquancafeapp.utils.IRecyclerViewOnItemClick;

import java.util.ArrayList;

public class AdminFragment extends Fragment implements IRecyclerViewOnItemClick {
    private FragmentAdminBinding binding;
    private AdminAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_admin, container, false);
        return binding.getRoot();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin(R.drawable.rounded_green,R.drawable.ic_speaker,"Báo cáo"));
        admins.add(new Admin(R.drawable.rounded_teal_200,R.drawable.ic_chair,"Bàn"));
        admins.add(new Admin(R.drawable.rounded_blue,R.drawable.ic_user,"Nhân viên"));
        admins.add(new Admin(R.drawable.rounded_orange,R.drawable.ic_store,"Kho"));
        adapter = new AdminAdapter(admins, this);
        binding.rvAdmin.setLayoutManager(new GridLayoutManager(getContext(), 2));
        binding.rvAdmin.setAdapter(adapter);
    }
    @Override
    public void onClick(Object model) {
        int position = (int) model;
        switch (position){
            case 0:
                Navigation.findNavController(getView()).navigate(R.id.reportFragment);
                break;
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
        }
    }
    @Override
    public void reductionBtn(int position) {

    }
}
