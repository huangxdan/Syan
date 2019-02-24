package com.app.sy.syan.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.remote.RemoteDataManager;
import com.app.sy.syan.login.LoginActivity;
import com.app.sy.syan.main.MainActivity;
import com.app.sy.syan.util.ActivityManager;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.PreferenceUtils;
import com.gyf.barlibrary.ImmersionBar;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.activity_guide_viewPager)
    ViewPager activityGuideViewPager;
    @BindView(R.id.splash_tv_countdown)
    TextView splashTvCountdown;
    @BindView(R.id.splash_view_bg_icon_container)
    RelativeLayout splashViewBgIconContainer;

    private CountDownTimer cdt = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityManager.instance().onCreate(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ImmersionBar.with(this).init();

        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);

        showAdvertise(4);

//        activityGuideViewPager.setAdapter(new ImagePagerAdapter(getSupportFragmentManager()));
//        activityGuideViewPager.setOffscreenPageLimit(guideFiles.size() - 1);


        RxView.clicks(splashTvCountdown)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        if (cdt != null) {
                            cdt.cancel();
                            joinApp();
                        }
                    }
                });
    }

    public void showAdvertise(int showTime) {
        splashTvCountdown.setVisibility(View.VISIBLE);

        cdt = new CountDownTimer(showTime * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //每秒变化一次TextView数字显示
                splashTvCountdown.setText("跳过 (" + (millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                splashTvCountdown.setText("跳过 (" + 0 + ")");
                joinApp();
            }
        };
        cdt.start();
    }

    public void joinApp() {

        if (!TextUtils.isEmpty(PreferenceUtils.getPrefString(SplashActivity.this, Constant.STAFF_NUMBER, "")) &&
                !TextUtils.isEmpty(PreferenceUtils.getPrefString(SplashActivity.this, Constant.LOGIN_NAME, "")) &&
                !TextUtils.isEmpty(PreferenceUtils.getPrefString(SplashActivity.this, Constant.LOGIN_PWD, ""))) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (cdt != null) {
            cdt.cancel();
        }
        finish();
    }

    public ArrayList<Integer> guideFiles = new ArrayList<>();

    private class ImagePagerAdapter extends FragmentStatePagerAdapter {

        public ImagePagerAdapter(FragmentManager fm) {
            super(fm);
//            guideFiles.add(R.drawable.pic_1);
//            guideFiles.add(R.drawable.pic_2);
//            guideFiles.add(R.drawable.pic_3);
//            guideFiles.add(R.drawable.pic_4);

//            guideFiles.add(R.drawable.pic_11);
        }

        @Override
        public int getCount() {
            return guideFiles == null ? 0 : guideFiles.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        @Override
        public Fragment getItem(int position) {
            return SplashGuideFragment.newInstance(guideFiles.get(position), position == guideFiles.size() - 1);
        }

    }

    @Override
    protected void onDestroy() {
        ActivityManager.instance().onDestroy(this);
        super.onDestroy();
    }
}
