package com.app.sy.syan.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.sy.syan.R;
import com.app.sy.syan.data.remote.RemoteDataManager;
import com.app.sy.syan.goods.GoodsActivity;
import com.app.sy.syan.introduce.IntroduceActivity;
import com.app.sy.syan.syan.SYanActivity;
import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    @BindView(R.id.imageView1)
    ImageView imageView1;
    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;


    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        Glide.with(getContext()).load(RemoteDataManager.HostAddress.BASE_ADDRESS + "shuntian.jpg").centerCrop().into(imageView1);
        Glide.with(getContext()).load(RemoteDataManager.HostAddress.BASE_ADDRESS + "yimeijia.png").centerCrop().into(imageView2);
        Glide.with(getContext()).load(RemoteDataManager.HostAddress.BASE_ADDRESS + "shengyan.jpg").centerCrop().into(imageView3);

        bindListener();

        return view;
    }

    private void bindListener() {
        RxView.clicks(imageView1)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(getContext(), IntroduceActivity.class));
                    }
                });
        RxView.clicks(imageView2)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(getContext(), GoodsActivity.class));
                    }
                });
        RxView.clicks(imageView3)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        startActivity(new Intent(getContext(), SYanActivity.class));
                    }
                });
    }

}
