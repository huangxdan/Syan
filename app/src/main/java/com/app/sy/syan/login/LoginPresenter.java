package com.app.sy.syan.login;

import android.content.Context;
import android.util.Log;

import com.app.sy.syan.R;
import com.app.sy.syan.data.bean.StaffInfoBean;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.LoginBody;
import com.app.sy.syan.util.L;
import com.google.gson.Gson;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * date 2018/5/8
 * version
 * describe
 *
 * @author hxd
 */
public class LoginPresenter implements LoginContract.Presenter {
    private static final String TAG = LoginPresenter.class.getSimpleName();
    private Context mContext;
    private SyanServiceApi mServiceApi;
    private LoginContract.View mView;

    @Inject
    public LoginPresenter(Context context, SyanServiceApi serviceApi, LoginContract.View view) {
        this.mContext = context;
        this.mServiceApi = serviceApi;
        this.mView = view;
    }

    @Override
    public void getData(String loginName, String loginPwd) {
        LoginBody loginBody = new LoginBody(loginName, loginPwd);

        mServiceApi.login(loginBody)
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
                        Log.v(TAG, "call: [responseBodyObservable :" + jsonStr + "]");
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
                        L.e(TAG, "onError: " + e.getMessage());
                        mView.showToast(e.getMessage());
                    }

                    @Override
                    public void onNext(StaffInfoBean staffInfoBean) {
                        if (staffInfoBean != null) {
                            if (staffInfoBean.code == 1000) {
                                mView.loginSuccess(staffInfoBean.getData());
                            } else if (staffInfoBean.code == 1002) {
                                mView.showToast("密码错误");
                            } else if (staffInfoBean.code == 1001) {
                                mView.showToast("账号不存在，请您联系管理员");
                            }

                        }
                    }
                });
    }
}
