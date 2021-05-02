package com.quanlyquancafeapp.presenter;

import android.content.Context;

import com.quanlyquancafeapp.db.DatabaseHelper;
import com.quanlyquancafeapp.model.User;
import com.quanlyquancafeapp.utils.Constance;
import com.quanlyquancafeapp.view.IHomeView;

import java.util.ArrayList;

public class HomePresenter {
    private IHomeView homeView;

    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }
    public void sell(){
        homeView.navigateToTableFragment();
    }
    public void payment(){homeView.navigateToProductFragment();}
}
