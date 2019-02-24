package com.app.sy.syan.love;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.data.LoveInfo;
import com.app.sy.syan.view.RaiseNumberAnimTextView;

import java.text.SimpleDateFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoveFragment extends Fragment implements LoveContract.View {
    private static final String TAG = LoveFragment.class.getSimpleName();

    @Inject
    LovePresenter mLovePresenter;
    @BindView(R.id.tv_year)
    TextView tvYear;
    @BindView(R.id.tv_mon)
    TextView tvMon;
    @BindView(R.id.tv_day)
    TextView tvDay;
    @BindView(R.id.raiseNumberAnimTextView)
    RaiseNumberAnimTextView raiseNumberAnimTextView;

    public static LoveFragment newInstance() {
        LoveFragment fragment = new LoveFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_love, container, false);
        ButterKnife.bind(this, view);

        DaggerLoveComponent.builder()
                .applicationComponent(SyanApplication.get(getContext()).getAppComponent())
                .loveModule(new LoveModule(this))
                .build()
                .inject(this);

        if (mLovePresenter != null) {
            mLovePresenter.getData();
        }


        return view;
    }

    @Override
    public void bindData(LoveInfo loveInfo) {
        if (loveInfo != null) {
            raiseNumberAnimTextView.setDuration(1500);
            raiseNumberAnimTextView.setFloatNumberWithAnim(loveInfo.getLoveFundMoney());

            SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy");
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MM");
            SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("dd");

            tvYear.setText(simpleDateFormat1.format(new Date(System.currentTimeMillis())));
            tvMon.setText(simpleDateFormat2.format(new Date(System.currentTimeMillis())));
            tvDay.setText(simpleDateFormat3.format(new Date(System.currentTimeMillis())));

        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (raiseNumberAnimTextView!=null){
            raiseNumberAnimTextView.clearAnimator();
        }
    }

}
