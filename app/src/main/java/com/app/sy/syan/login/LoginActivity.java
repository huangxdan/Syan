package com.app.sy.syan.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.base.BaseActivity;
import com.app.sy.syan.data.StaffInfo;
import com.app.sy.syan.main.MainActivity;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.PreferenceUtils;
import com.app.sy.syan.view.NavigationBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class LoginActivity extends BaseActivity implements NavigationBar.NavigationBarInteface, LoginContract.View {

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
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_note)
    TextView btnNote;

    @Inject
    LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        DaggerLoginComponent.builder()
                .applicationComponent(SyanApplication.get(this).getAppComponent())
                .loginModule(new LoginModule(this))
                .build()
                .inject(this);
        initView();


        RxView.clicks(btnNote)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        executeLogin();
                    }
                });
    }

    private void initView() {
        NavigationBar navigationBar = new NavigationBar(this, this);
        navigationBar.setCenterItemIconShow();
        navigationBar.setLeftItemIconHidden();
        navigationBar.setRightItemHidden();
        navigationBar.setCenterTitle("登  录");
        navigationBar.setCenterItemIconHidden();
    }

    private void executeLogin() {
        String name = etName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("用户名不能为空");
            return;
        }

        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            showToast("密码不能为空");
            return;
        }

        if (loginPresenter != null) {
            loginPresenter.getData(name, pwd);
        }

    }

    @Override
    public void onNavigationLeftItemClick() {

    }

    @Override
    public void onNavigationRightItemClick() {

    }

    @Override
    public void onNavigationCenterItemClick() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(StaffInfo staffInfo) {
        PreferenceUtils.setPrefString(LoginActivity.this, Constant.STAFF_NUMBER, staffInfo.getStaffNumber());
        PreferenceUtils.setPrefString(LoginActivity.this, Constant.LOGIN_NAME, staffInfo.getLoginName());
        PreferenceUtils.setPrefString(LoginActivity.this, Constant.LOGIN_PWD, staffInfo.getLoginPwd());
        startActivity(new Intent(this, MainActivity.class));
    }
}
