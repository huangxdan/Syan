package com.app.sy.syan.mine.car;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.GoodsInfoListBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.CharacterBody;
import com.app.sy.syan.data.request.UpdateCartNumBody;
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

public class CarPresenter implements CarContract.Presenter {
    private static final String TAG = CarPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private CarContract.View mView;

    @Inject
    public CarPresenter(Context mContext, SyanServiceApi mServiceApi, CarContract.View mView) {
        this.mContext = mContext;
        this.mServiceApi = mServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {
        CharacterBody characterBody = new CharacterBody(PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""));
        mServiceApi.getCartList(characterBody)
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
                .map(new Func1<String, GoodsInfoListBean>() {
                    @Override
                    public GoodsInfoListBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, GoodsInfoListBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsInfoListBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsInfoListBean object) {
                        if (object != null && object.getData() != null) {
                            mView.bindData(object.getData());
                        }
                    }
                });
    }


    @Override
    public void updateCartNum(String productId, String goodsNum) {
        UpdateCartNumBody body = new UpdateCartNumBody(PreferenceUtils.getPrefString(mContext, Constant.STAFF_NUMBER, ""), productId, goodsNum);
        mServiceApi.updateCartNum(body)
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
//                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(RPCMessage countBean) {

                    }
                });
    }
}
