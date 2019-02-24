package com.app.sy.syan.setting;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.login.LoginActivity;
import com.app.sy.syan.mine.address.ModifyAddressActivity;
import com.app.sy.syan.mine.modify.ModifyActivity;
import com.app.sy.syan.util.ActivityManager;
import com.app.sy.syan.util.PreferenceUtils;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class SettingActivity extends BaseActivity implements NavigationBar.NavigationBarInteface {

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
    @BindView(R.id.rl_modify_psw)
    RelativeLayout rlModifyPsw;
    @BindView(R.id.rl_exit)
    RelativeLayout rlExit;
    @BindView(R.id.rl_modify_address)
    RelativeLayout rlModifyAddress;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        context = this;
        ButterKnife.bind(this);
        initView();
        rxBind();
    }

    private void rxBind() {
        RxView.clicks(rlModifyPsw)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(context, ModifyActivity.class));
                    }
                });

        RxView.clicks(rlModifyAddress)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(context, ModifyAddressActivity.class));
                    }
                });

        RxView.clicks(rlExit)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        PreferenceUtils.clearPreference(context, PreferenceManager.getDefaultSharedPreferences(getApplicationContext()));
                        ActivityManager.instance().finishActivities();
                        startActivity(new Intent(context, LoginActivity.class));
                    }
                });
    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemTitleHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("设置");
        navigationBar.setCenterItemIconHidden();
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
