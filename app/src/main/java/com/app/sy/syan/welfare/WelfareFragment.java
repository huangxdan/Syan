package com.app.sy.syan.welfare;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.sy.syan.R;
import com.app.sy.syan.welfare.character.CharacterActivity;
import com.app.sy.syan.welfare.extract.ExtractActivity;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class WelfareFragment extends Fragment {
    private static final String TAG = WelfareFragment.class.getSimpleName();
    @BindView(R.id.tv_character)
    TextView tvCharacter;
    @BindView(R.id.tv_welfare)
    TextView tvWelfare;
    @BindView(R.id.ll_character)
    LinearLayout llCharacter;
    @BindView(R.id.ll_welfare)
    LinearLayout llWelfare;


    public static WelfareFragment newInstance() {
        WelfareFragment fragment = new WelfareFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_welfare, container, false);
        ButterKnife.bind(this, view);

        RxView.clicks(llCharacter)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getActivity().startActivity(new Intent(getContext(), CharacterActivity.class));
                    }
                });

        RxView.clicks(llWelfare)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getActivity().startActivity(new Intent(getContext(), ExtractActivity.class));
                    }
                });


        return view;
    }

}
