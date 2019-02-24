package com.app.sy.syan.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import rx.functions.Action1;

public class SplashGuideFragment extends Fragment {
    private ImageView iv_splash_guide;
    private TextView iv_splash_guide_jump;
    private int res;
    private boolean showButton;


    public static SplashGuideFragment newInstance(int res, boolean showButton) {
        final SplashGuideFragment f = new SplashGuideFragment();
        final Bundle args = new Bundle();
        args.putInt("res", res);
        args.putBoolean("showbutton", showButton);
        f.setArguments(args);
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_splash_guide, container, false);
        iv_splash_guide = (ImageView) rootView.findViewById(R.id.iv_splash_guide);
        iv_splash_guide_jump = (TextView) rootView.findViewById(R.id.iv_splash_guide_jump);
        initView();
        return rootView;
    }

    private void initView() {
        this.res = getArguments() != null ? getArguments().getInt("res") : 0;
        this.showButton = getArguments() != null ? getArguments().getBoolean("showbutton") : false;
        if (this.res != 0) {
            Glide.with(getActivity()).load(res).centerCrop().into(iv_splash_guide);
        }

//        if (showButton) {
//            iv_splash_guide_jump.setVisibility(View.VISIBLE);
//            RxView.clicks(iv_splash_guide_jump).throttleFirst(10, TimeUnit.SECONDS).subscribe(new Action1<Object>() {
//                @Override
//                public void call(Object o) {
//                    ((SplashActivity) getActivity()).joinApp();
//                }
//            });
//        }
    }
}
