package com.app.sy.syan.love;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.LoveInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LovePresenter implements LoveContract.Presenter {
    private static final String TAG = LovePresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private LoveContract.View mView;

    @Inject
    public LovePresenter(Context context, SyanServiceApi mSyanServiceApi, LoveContract.View mView) {
        this.context = context;
        this.mSyanServiceApi = mSyanServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {
        mSyanServiceApi.getLoveFee()
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
                .map(new Func1<String, LoveInfoBean>() {
                    @Override
                    public LoveInfoBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, LoveInfoBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LoveInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(LoveInfoBean object) {
                        if (object != null) {
                            if (object.code == 1000) {
                                mView.bindData(object.getData());
                            }
                        }
                    }
                });
    }
}
