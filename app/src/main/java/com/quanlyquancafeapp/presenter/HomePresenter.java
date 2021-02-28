package com.quanlyquancafeapp.presenter;

import com.quanlyquancafeapp.view.IHomeView;

public class HomePresenter {
    private IHomeView homeView;
    public HomePresenter(IHomeView homeView) {
        this.homeView = homeView;
    }
    public void sell(){
        homeView.navigateToTableFragment();
    }
}
