package com.quanlyquancafeapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.quanlyquancafeapp.R;
import com.quanlyquancafeapp.adapter.AdminAdapter;
import com.quanlyquancafeapp.databinding.FragmentAdminBinding;
import com.quanlyquancafeapp.model.Admin;

import java.util.ArrayList;

public class AdminFragment extends Fragment {
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
        admins.add(new Admin(R.color.blue,R.drawable.ic_speaker,"Báo cáo"));
        //handle add data admin
        adapter = new AdminAdapter(admins);
        binding.rvAdmin.setLayoutManager(new GridLayoutManager(getContext(), 2)); //add data background color item_admin
        binding.rvAdmin.setAdapter(adapter);
    }
}
