package com.app.sy.syan.goods;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.GoodsInfoListBean;
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

public class GoodsPresenter implements GoodsContract.Presenter {
    private static final String TAG = GoodsPresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private GoodsContract.View mView;

    @Inject
    public GoodsPresenter(Context context, SyanServiceApi syanServiceApir, GoodsContract.View view) {
        this.context = context;
        this.mSyanServiceApi = syanServiceApir;
        this.mView = view;
    }

    @Override
    public void getDate() {
        mSyanServiceApi.productList()
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
                        mView.showNoNet();
                        mView.hideLoading();
                    }

                    @Override
                    public void onNext(GoodsInfoListBean goodsInfoListBean) {
                        mView.hideLoading();
                        if (goodsInfoListBean != null && goodsInfoListBean.getData()!= null) {
                                mView.bindData(goodsInfoListBean.getData());
                        }
                    }
                });
    }
}
