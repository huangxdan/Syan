package com.app.sy.syan.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.app.sy.syan.R;
import com.app.sy.syan.util.ActivityManager;
import com.gyf.barlibrary.ImmersionBar;


public abstract class BaseActivity extends AppCompatActivity {

    //dfc289

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        ActivityManager.instance().onCreate(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        ImmersionBar.with(this)
                .statusBarColor(R.color.dfc289)
                .navigationBarColor(R.color.dfc289)
                .statusBarDarkFont(true,0.2f)
                .fitsSystemWindows(true)
        .init();

    }

    @Override
    protected void onDestroy() {
        //必须调用该方法，防止内存泄漏
        ImmersionBar.with(this).destroy();
        ActivityManager.instance().onDestroy(this);
        super.onDestroy();

    }
}
