package com.app.sy.syan.mine.modify;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.ModifyPwdBody;
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

public class ModifyPresenter implements ModifyContract.Presenter {
    private static final String TAG = ModifyPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private ModifyContract.View mView;

    @Inject
    public ModifyPresenter(Context context, SyanServiceApi serviceApi, ModifyContract.View view) {
        this.mContext = context;
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void modify(String oldPwd, String newPwd) {
        ModifyPwdBody modifyPwdBody = new ModifyPwdBody(
                PreferenceUtils.getPrefString(mContext, Constant.LOGIN_NAME, ""), oldPwd, newPwd);

        mServiceApi.modifyPwd(modifyPwdBody)
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
                    }

                    @Override
                    public void onNext(RPCMessage object) {
                        if (object != null) {
                            if (object.code == 1000) {
                                mView.modufySuccess();
                            } else {
                                mView.showToast("修改失败");
                            }
                        }
                    }
                });
    }
}
