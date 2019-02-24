package com.app.sy.syan.introduce;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.IntroduceInfoBean;
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

public class IntroducePresenter implements IntroduceContract.Presenter {
    private static final String TAG = IntroducePresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private IntroduceContract.View mView;

    @Inject
    public IntroducePresenter(Context context, SyanServiceApi mSyanServiceApi, IntroduceContract.View mView) {
        this.context = context;
        this.mSyanServiceApi = mSyanServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {
        mSyanServiceApi.getIntroduce()
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
                .map(new Func1<String, IntroduceInfoBean>() {
                    @Override
                    public IntroduceInfoBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, IntroduceInfoBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IntroduceInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(IntroduceInfoBean object) {
                        if (object != null) {
                            if (object.code == 1000) {
                                mView.bindData(object.getData());
                            }
                        }
                    }
                });
    }
}
