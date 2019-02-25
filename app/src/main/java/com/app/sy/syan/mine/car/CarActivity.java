package com.app.sy.syan.mine.car;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class CarActivity extends BaseActivity implements CarContract.View, NavigationBar.NavigationBarInteface {

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
    @BindView(R.id.view_bottom_line)
    View viewBottomLine;
    @BindView(R.id.shop_cart_select_all)
    CheckBox shopCartSelectAll;
    @BindView(R.id.shop_cart_all_count)
    TextView shopCartAllCount;
    @BindView(R.id.ll_shop_cart_account)
    LinearLayout llShopCartAccount;
    @BindView(R.id.tv_shop_cart_price)
    TextView tvShopCartPrice;
    @BindView(R.id.ll_cart_price)
    LinearLayout llCartPrice;
    @BindView(R.id.rel_cart_bottom)
    RelativeLayout relCartBottom;
    @BindView(R.id.iv_cart_no_data)
    ImageView ivCartNoData;
    @BindView(R.id.cart_no_data)
    RelativeLayout cartNoData;

    @Inject
    CarPresenter mPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    RecyclerAdapterWithHF recyclerAdapterWithHF;
    @Inject
    CarGoodsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
        ButterKnife.bind(this);
        initView();

        DaggerCarComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .carModule(new CarModule(this))
                .build()
                .inject(this);

        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(recyclerAdapterWithHF);

        RxView.clicks(llShopCartAccount)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //结算
                    }
                });
    }


    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("购物车");
        navigationBar.setCenterItemIconHidden();
    }

    @Override
    public void bindData() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(CarActivity.this, msg, Toast.LENGTH_SHORT).show();
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
