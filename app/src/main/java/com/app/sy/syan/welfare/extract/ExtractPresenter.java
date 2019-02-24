package com.app.sy.syan.welfare.extract;

import android.content.Context;
import android.util.Log;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.WelfareInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.CharacterBody;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ExtractPresenter implements ExtractContract.Presenter {
    private static final String TAG = ExtractPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private ExtractContract.View mView;

    @Inject
    public ExtractPresenter(Context context, SyanServiceApi serviceApi, ExtractContract.View view) {
        this.mContext = context;
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void getData(String staffNumber) {
        CharacterBody characterBody = new CharacterBody(staffNumber);

        mServiceApi.todayWelfare(characterBody)
                .onErrorReturn(new Func1<Throwable, ResponseBody>() {
                    @Override
                    public ResponseBody call(Throwable throwable) {
                        throw new RuntimeException(mContext.getString(R.string.net_error));
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
                .map(new Func1<String, WelfareInfoBean>() {
                    @Override
                    public WelfareInfoBean call(String jsonStr) {
                        Log.v(TAG, "call: [responseBodyObservable :" + jsonStr + "]");
                        return new Gson().fromJson(jsonStr, WelfareInfoBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<WelfareInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(WelfareInfoBean welfareInfoBean) {
                        if (welfareInfoBean != null) {
                            mView.bindDate(welfareInfoBean.getData());
                        }
                    }
                });
    }
}
