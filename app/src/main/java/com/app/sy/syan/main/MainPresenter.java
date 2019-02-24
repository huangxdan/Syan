package com.app.sy.syan.main;

import android.content.Context;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    private Context context;

    @Inject
    public MainPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void getData() {

    }
}
