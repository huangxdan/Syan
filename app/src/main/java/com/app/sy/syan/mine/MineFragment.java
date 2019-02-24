package com.app.sy.syan.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sy.syan.R;
import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.data.StaffInfo;
import com.app.sy.syan.data.event.ModifyAddressEvent;
import com.app.sy.syan.mine.car.CarActivity;
import com.app.sy.syan.mine.order.OrderActivity;
import com.app.sy.syan.setting.SettingActivity;
import com.app.sy.syan.view.LoadingDialog;
import com.app.sy.syan.view.MyScrollView;
import com.jakewharton.rxbinding.view.RxView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

public class MineFragment extends Fragment implements MineContract.View {
    private static final String TAG = MineFragment.class.getSimpleName();
    @BindView(R.id.rl_setting)
    RelativeLayout rlSetting;
    @BindView(R.id.tv_staff_number)
    TextView tvStaffNumber;
    @BindView(R.id.tv_personal_nick)
    TextView tvPersonalNick;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.tv_card_number)
    TextView tvCardNumber;
    @BindView(R.id.rl_card_number)
    RelativeLayout rlCardNumber;
    @BindView(R.id.tv_back_card_number)
    TextView tvBackCardNumber;
    @BindView(R.id.rl_back_card_number)
    RelativeLayout rlBackCardNumber;
    @BindView(R.id.tv_affiliated_bank_number)
    TextView tvAffiliatedBankNumber;
    @BindView(R.id.rl_affiliated_bank_number)
    RelativeLayout rlAffiliatedBankNumber;
    @BindView(R.id.tv_opening_bank_number)
    TextView tvOpeningBankNumber;
    @BindView(R.id.rl_opening_bank_number)
    RelativeLayout rlOpeningBankNumber;
    @BindView(R.id.tv_addr)
    TextView tvAddr;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.rl_address)
    RelativeLayout rlAddress;
    @BindView(R.id.myScrollView)
    MyScrollView myScrollView;
    @BindView(R.id.btn_reload)
    Button btnReload;
    @BindView(R.id.ll_no_net)
    LinearLayout llNoNet;
    @BindView(R.id.rl_car)
    RelativeLayout rlCar;
    @BindView(R.id.rl_order)
    RelativeLayout rlOrder;


    private LoadingDialog mLoadingDialog;

    @Inject
    MinePresenter minePresenter;

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, container, false);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);
        DaggerMineComponent.builder()
                .applicationComponent(SyanApplication.get(getContext()).getAppComponent())
                .mineModule(new MineModule(this))
                .build()
                .inject(this);

        if (minePresenter != null) {
            showLoading();
            minePresenter.getData();
        }
        rxBind();
        return view;
    }

    private void rxBind() {
        RxView.clicks(rlSetting)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getActivity().startActivity(new Intent(getContext(), SettingActivity.class));
                    }
                });

        RxView.clicks(btnReload).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        if (minePresenter != null) {
                            llNoNet.setVisibility(View.GONE);
                            showLoading();
                            minePresenter.getData();
                        }
                    }
                });

        RxView.clicks(rlCar)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getActivity().startActivity(new Intent(getContext(), CarActivity.class));
                    }
                });


        RxView.clicks(rlOrder)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        getActivity().startActivity(new Intent(getContext(), OrderActivity.class));
                    }
                });

    }


    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(getContext());
        }
        mLoadingDialog.showDialog();
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog.closeDialog();
        }
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindDate(StaffInfo staffInfo) {
        llNoNet.setVisibility(View.GONE);
        myScrollView.setVisibility(View.VISIBLE);
        if (staffInfo != null) {
            tvPersonalNick.setText(staffInfo.getStaffName());
            tvStaffNumber.setText("编号：" + staffInfo.getStaffNumber());
            tvPhoneNumber.setText(staffInfo.getPhonenumber());
            tvCardNumber.setText(staffInfo.getCardnumber());
            tvBackCardNumber.setText(staffInfo.getBankCardNumber());
            tvAffiliatedBankNumber.setText(staffInfo.getAffiliatedBank());
            tvOpeningBankNumber.setText(staffInfo.getOpeningBank());
            tvAddress.setText(staffInfo.getAddress());
        }
    }

    @Override
    public void showNoNet() {
        llNoNet.setVisibility(View.VISIBLE);
        myScrollView.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(ModifyAddressEvent event) {
        if (event != null) {
            if (event.isModifySuccess()) {
                if (minePresenter != null) {
                    minePresenter.getData();
                }
            }
        }
    }

}
