package com.app.sy.syan.mine.order;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.MyOrderListBean;
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

public class OrderPresenter implements OrderContract.Presenter {
    private static final String TAG = OrderPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private OrderContract.View mView;

    @Inject
    public OrderPresenter(Context mContext, SyanServiceApi mServiceApi, OrderContract.View mView) {
        this.mContext = mContext;
        this.mServiceApi = mServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {
        CharacterBody characterBody = new CharacterBody(PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""));

        mServiceApi.getOrderList(characterBody)
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
                .map(new Func1<String, MyOrderListBean>() {
                    @Override
                    public MyOrderListBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, MyOrderListBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyOrderListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                        mView.showNoNet();
                    }

                    @Override
                    public void onNext(MyOrderListBean object) {
                        if (object != null && object.getData() != null) {
                            mView.bindData(object.getData());
                        }
                    }
                });
    }
}
