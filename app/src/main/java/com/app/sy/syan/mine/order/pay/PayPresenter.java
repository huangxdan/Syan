package com.app.sy.syan.mine.order.pay;

import android.content.Context;

import com.app.sy.syan.data.remote.SyanServiceApi;

import javax.inject.Inject;

public class PayPresenter implements PayContract.Presenter {
    private static final String TAG = PayPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private PayContract.View mView;

    @Inject
    public PayPresenter(Context mContext, SyanServiceApi mServiceApi, PayContract.View mView) {
        this.mContext = mContext;
        this.mServiceApi = mServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData(String address) {
//        ModifyAddressBody modifyAddressBody = new ModifyAddressBody(
//                PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""), address);
//
//        mServiceApi.modifyAddress(modifyAddressBody)
//                .onErrorReturn(new Func1<Throwable, ResponseBody>() {
//                    @Override
//                    public ResponseBody call(Throwable throwable) {
//                        throw new RuntimeException(mContext.getString(R.string.net_error));
//                    }
//                })
//                .map(new Func1<ResponseBody, String>() {
//                    @Override
//                    public String call(ResponseBody responseBody) {
//                        String jsonStr = "";
//                        try {
//                            jsonStr = responseBody.string();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        RPCMessage message = new Gson().fromJson(jsonStr, RPCMessage.class);
//                        if (message == null) {
//                            throw new NullPointerException("获取数据失败");
//                        }
//
//                        return jsonStr;
//                    }
//                })
//                .map(new Func1<String, RPCMessage>() {
//                    @Override
//                    public RPCMessage call(String jsonStr) {
//                        return new Gson().fromJson(jsonStr, RPCMessage.class);
//                    }
//                })
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<RPCMessage>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        mView.showToast(e.getMessage());
//                    }
//
//                    @Override
//                    public void onNext(RPCMessage object) {
//                        if (object != null) {
//                            if (object.code == 1000) {
//                                mView.modifySuccess();
//                            } else {
//                                mView.showToast("修改失败");
//                            }
//                        }
//                    }
//                });
    }
}
