package com.app.sy.syan.introduce;

import android.os.Bundle;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.IntroduceInfo;
import com.app.sy.syan.view.NavigationBar;

import javax.inject.Inject;

public class IntroduceActivity extends BaseActivity implements IntroduceContract.View, NavigationBar.NavigationBarInteface {

    @Inject
    IntroducePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce);

        DaggerIntroduceComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .introduceModule(new IntroduceModule(this))
                .build()
                .inject(this);

//        if (presenter != null) {
//            presenter.getData();
//        }
        initView();
    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("公司介绍");
        navigationBar.setCenterItemIconHidden();
    }

    @Override
    public void bindData(IntroduceInfo goodsInfo) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(IntroduceActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNavigationLeftItemClick() {
        finish();
    }

    @Override
    public void onNavigationRightItemClick() {

    }

    @Override
    public void onNavigationCenterItemClick() {

    }
}
