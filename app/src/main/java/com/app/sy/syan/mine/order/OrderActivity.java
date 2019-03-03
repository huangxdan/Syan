package com.app.sy.syan.mine.order;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.MyOrderList;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class OrderActivity extends BaseActivity implements OrderContract.View, NavigationBar.NavigationBarInteface {

    @BindView(R.id.pub_navi_left_item_icon)
    ImageView pubNaviLeftItemIcon;
    @BindView(R.id.pub_navi_left_item_title)
    TextView pubNaviLeftItemTitle;
    @BindView(R.id.pub_navi_left_item)
    RelativeLayout pubNaviLeftItem;
    @BindView(R.id.pub_navi_center_item_title)
    TextView pubNaviCenterItemTitle;
    @BindView(R.id.pub_navi_center_item_icon)
    ImageView pubNaviCenterItemIcon;
    @BindView(R.id.pub_navi_center_item)
    RelativeLayout pubNaviCenterItem;
    @BindView(R.id.pub_navi_right_item_icon)
    ImageView pubNaviRightItemIcon;
    @BindView(R.id.pub_navi_right_item_title)
    TextView pubNaviRightItemTitle;
    @BindView(R.id.pub_navi_right_item)
    RelativeLayout pubNaviRightItem;
    @BindView(R.id.ll_navi_root)
    LinearLayout llNaviRoot;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.ll_no_net)
    LinearLayout ll404;
    @BindView(R.id.btn_reload)
    Button btnReload;

    @Inject
    OrderPresenter mPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    RecyclerAdapterWithHF recyclerAdapterWithHF;
    @Inject
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        DaggerOrderComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .orderModule(new OrderModule(this))
                .build()
                .inject(this);

        initView();

        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(recyclerAdapterWithHF);

        mPresenter.getData();

        RxView.clicks(btnReload).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (mPresenter != null) {
                            ll404.setVisibility(View.GONE);
                            showLoading();
                            //获取购物车列表
                            mPresenter.getData();
                        }
                    }
                });
    }


    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("我的订单");
        navigationBar.setCenterItemIconHidden();
    }

    @Override
    public void bindData(List<MyOrderList> lists) {
        ll404.setVisibility(View.GONE);

        mAdapter.setData(lists);
    }

    @Override
    public void showNoNet() {
        ll404.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(OrderActivity.this, msg, Toast.LENGTH_SHORT).show();
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
