package com.app.sy.syan.mine.car;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.event.ModifyAddressEvent;
import com.app.sy.syan.mine.address.ModifyAddressPresenter;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;

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
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.btn_note)
    TextView btnNote;

    @Inject
    CarPresenter mPresenter;

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

        RxView.clicks(btnNote)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

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
