package com.app.sy.syan.mine;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.StaffInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.CharacterBody;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.PreferenceUtils;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MinePresenter implements MineContract.Presenter {
    private static final String TAG = MinePresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private MineContract.View mView;

    @Inject
    public MinePresenter(Context context, SyanServiceApi syanServiceApi, MineContract.View view) {
        this.context = context;
        this.mSyanServiceApi = syanServiceApi;
        this.mView = view;
    }

    @Override
    public void getData() {
        CharacterBody characterBody = new CharacterBody(PreferenceUtils.getPrefString(context, Constant.STAFF_NUMBER, ""));
        mSyanServiceApi.mine(characterBody)
                .onErrorReturn(new Func1<Throwable, ResponseBody>() {
                    @Override
                    public ResponseBody call(Throwable throwable) {
                        throw new RuntimeException(context.getString(R.string.net_error));
                    }
                })
                .map(new Func1<ResponseBody, String>() {
                    @Override
                    public String call(ResponseBody responseBody) {
                        String jsonStr = "";
                        try {
                            jsonStr = responseBody.string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        RPCMessage message = new Gson().fromJson(jsonStr, RPCMessage.class);
                        if (message == null) {
                            throw new NullPointerException("获取数据失败");
                        }

                        return jsonStr;
                    }
                })
                .map(new Func1<String, StaffInfoBean>() {
                    @Override
                    public StaffInfoBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, StaffInfoBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StaffInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                        mView.showNoNet();
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(StaffInfoBean staffInfoBean) {
                        mView.hideLoading();
                        if (staffInfoBean != null) {
                            if (staffInfoBean.code == 1000) {
                                mView.bindDate(staffInfoBean.getData());
                            }
                        }
                    }
                });
    }

}
