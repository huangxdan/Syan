package com.app.sy.syan.mine.car;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.util.DataTransfer;
import com.app.sy.syan.util.NumberUtil;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class CarActivity extends BaseActivity implements CarContract.View, NavigationBar.NavigationBarInteface
        , CarGoodsAdapter.UpDateTotalPrice {

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
    @BindView(R.id.ll_no_net)
    LinearLayout ll404;
    @BindView(R.id.btn_reload)
    Button btnReload;

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

        DaggerCarComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .carModule(new CarModule(this))
                .build()
                .inject(this);

        initView();
        bindListener();

        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(recyclerAdapterWithHF);
        mAdapter.setPresenter(mPresenter);
        mAdapter.setUpDateTotalPrice(this);

        //获取购物车列表
        mPresenter.getData();
    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("购物车");
        navigationBar.setCenterItemIconHidden();
    }

    private void bindListener() {

        RxView.clicks(llShopCartAccount)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //结算
                    }
                });

        shopCartSelectAll.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //全选
                    upDate(DataTransfer.getInstance().cartGoods);

                } else {
                    //全不选
                    upDate(new ArrayList<GoodsInfo>());
                }
                for (int i = 0; i < DataTransfer.getInstance().cartGoods.size(); i++) {
                    CarGoodsAdapter.ViewHolder childViewHolder = (CarGoodsAdapter.ViewHolder) recycleview.getChildViewHolder(recycleview.getChildAt(i));
                    childViewHolder.checkbox.setChecked(isChecked);
                }
            }
        });

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

    @Override
    public void bindData(List<GoodsInfo> list) {
        ll404.setVisibility(View.GONE);
        recycleview.setVisibility(View.VISIBLE);
        DataTransfer.getInstance().cartGoods.clear();
        DataTransfer.getInstance().cartGoods.addAll(list);
        mAdapter.setData();
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
    public void showNoNet() {
        ll404.setVisibility(View.VISIBLE);
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

    @Override
    public void upDate(List<GoodsInfo> selectGoods) {
        double totalMoney = 0;
        int totalCount = 0;
        //更新总价钱
        for (int i = 0; i < selectGoods.size(); i++) {
            totalCount += selectGoods.get(i).getGoodscount();
            totalMoney += (selectGoods.get(i).getProductPrice() * selectGoods.get(i).getGoodscount());
        }

        tvShopCartPrice.setText(NumberUtil.getDoubleString(totalMoney));
        shopCartAllCount.setText(totalCount + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataTransfer.getInstance().cartGoods.clear();
    }
}
