package com.app.sy.syan.welfare.character;

import android.content.Context;
import android.util.Log;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.CharacterInfobean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.CharacterBody;
import com.app.sy.syan.util.L;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class CharacterPresenter implements CharacterContract.Presenter {
    private static final String TAG = CharacterPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private CharacterContract.View mView;

    @Inject
    public CharacterPresenter(Context context, SyanServiceApi serviceApi, CharacterContract.View view) {
        this.mContext = context;
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void getData(String staffNumber) {
        CharacterBody characterBody = new CharacterBody(staffNumber);

        mServiceApi.character(characterBody)
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
                .map(new Func1<String, CharacterInfobean>() {
                    @Override
                    public CharacterInfobean call(String jsonStr) {
                        Log.v(TAG, "call: [responseBodyObservable :" + jsonStr + "]");
                        return new Gson().fromJson(jsonStr, CharacterInfobean.class);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CharacterInfobean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        L.e(TAG, "onError: " + e.getMessage());

                    }

                    @Override
                    public void onNext(CharacterInfobean characterInfobean) {
                        if (characterInfobean != null) {
                            mView.bindData(characterInfobean.getData());
                        }
                    }
                });
    }
}
