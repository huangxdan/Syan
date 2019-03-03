package com.app.sy.syan.mine.order.confirm;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.data.StaffInfo;
import com.app.sy.syan.util.RecyclerAdapterWithHF;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class ConfirmActivity extends BaseActivity implements ConfirmContract.View, NavigationBar.NavigationBarInteface {

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
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_receiver_phone)
    TextView tvReceiverPhone;
    @BindView(R.id.tv_receiver_address)
    TextView tvReceiverAddress;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.recycleview)
    RecyclerView recycleview;
    @BindView(R.id.ll_to_pay)
    LinearLayout llToPay;

    @Inject
    ConfirmPresenter mPresenter;
    @Inject
    LinearLayoutManager linearLayoutManager;
    @Inject
    RecyclerAdapterWithHF recyclerAdapterWithHF;
    @Inject
    ConfirmGoodsAdapter mAdapter;

    private List<GoodsInfo> mGoodsList;
    private String totalMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);
        ButterKnife.bind(this);

        DaggerConfirmComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .confirmModule(new ConfirmModule(this))
                .build()
                .inject(this);

        initView();
        initData();
        bindListener();

    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("确认订单");
        navigationBar.setCenterItemIconHidden();
    }

    private void initData() {
        mPresenter.getData();

        recycleview.setLayoutManager(linearLayoutManager);
        recycleview.setAdapter(recyclerAdapterWithHF);

        mGoodsList = (List<GoodsInfo>) getIntent().getSerializableExtra("list");
        mAdapter.setData(mGoodsList);
        totalMoney = getIntent().getStringExtra("totalPrice");
        tvTotalMoney.setText("¥ " + totalMoney);
    }

    private void bindListener() {

        RxView.clicks(llToPay)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        //提交订单
                        mPresenter.addMyOrder(mGoodsList, totalMoney);
                    }
                });
    }

    @Override
    public void bindData(StaffInfo staffInfo) {
        tvReceiver.setText(staffInfo.getStaffName());
        tvReceiverPhone.setText(staffInfo.getPhonenumber());
        tvReceiverAddress.setText(staffInfo.getAddress());
    }

    @Override
    public void confirmSuccess() {
        setResult(2);
        finish();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(ConfirmActivity.this, msg, Toast.LENGTH_SHORT).show();
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
