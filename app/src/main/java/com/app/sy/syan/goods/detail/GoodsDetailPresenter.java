package com.app.sy.syan.goods.detail;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.GoodsInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.ProductDetailBody;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class GoodsDetailPresenter implements GoodsDetailContract.Presenter {
    private static final String TAG = GoodsDetailPresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private GoodsDetailContract.View mView;

    @Inject
    public GoodsDetailPresenter(Context context, SyanServiceApi syanServiceApir, GoodsDetailContract.View view) {
        this.context = context;
        this.mSyanServiceApi = syanServiceApir;
        this.mView = view;
    }

    @Override
    public void getData(String productId) {
        ProductDetailBody body = new ProductDetailBody(productId);
        mSyanServiceApi.productDetail(body)
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
                .map(new Func1<String, GoodsInfoBean>() {
                    @Override
                    public GoodsInfoBean call(String jsonStr) {
                        return new Gson().fromJson(jsonStr, GoodsInfoBean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<GoodsInfoBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(GoodsInfoBean goodsInfoBean) {
                        if (goodsInfoBean != null) {
                            mView.bindData(goodsInfoBean.getData());
                        }
                    }
                });
    }
}

