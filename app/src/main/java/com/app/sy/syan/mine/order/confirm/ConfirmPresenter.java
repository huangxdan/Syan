package com.app.sy.syan.mine.order.confirm;

import android.content.Context;
import android.text.TextUtils;

import com.app.sy.syan.R;
import com.app.sy.syan.data.GoodsInfo;
import com.app.sy.syan.data.bean.StaffInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.CharacterBody;
import com.app.sy.syan.data.request.SubmitOrderBody;
import com.app.sy.syan.util.Constant;
import com.app.sy.syan.util.NumberUtil;
import com.app.sy.syan.util.PreferenceUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class ConfirmPresenter implements ConfirmContract.Presenter {
    private static final String TAG = ConfirmPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private ConfirmContract.View mView;

    @Inject
    public ConfirmPresenter(Context mContext, SyanServiceApi mServiceApi, ConfirmContract.View mView) {
        this.mContext = mContext;
        this.mServiceApi = mServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {
        CharacterBody characterBody = new CharacterBody(PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""));
        mServiceApi.mine(characterBody)
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
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(StaffInfoBean staffInfoBean) {
                        mView.hideLoading();
                        if (staffInfoBean != null) {
                            if (staffInfoBean.code == 1000) {
                                mView.bindData(staffInfoBean.getData());
                            }
                        }
                    }
                });
    }

    @Override
    public void addMyOrder(List<GoodsInfo> list, String totalMoney) {
        List<SubmitOrderBody.OrderInfo> orderInfos = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            orderInfos.add(new SubmitOrderBody.OrderInfo(list.get(i).getProductId(),
                    list.get(i).getGoodscount(), NumberUtil.getDoubleString(list.get(i).getGoodscount() * list.get(i).getProductPrice())));
        }
        SubmitOrderBody submitOrderBody = new SubmitOrderBody(PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""),
                totalMoney, orderInfos);
        mServiceApi.addMyOrder(submitOrderBody)
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
                .map(new Func1<String, RPCMessage>() {
                    @Override
                    public RPCMessage call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, RPCMessage.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RPCMessage>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(RPCMessage rpcMessage) {
                        mView.hideLoading();
                        if (rpcMessage != null) {
                            if (rpcMessage.code == 1000) {
                                mView.showToast(TextUtils.isEmpty(rpcMessage.getMsg()) ? "订单创建成功" : rpcMessage.getMsg());
                                mView.confirmSuccess();
                            }
                        }
                    }
                });
    }
}
