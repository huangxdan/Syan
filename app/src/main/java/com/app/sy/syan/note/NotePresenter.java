package com.app.sy.syan.note;

import android.content.Context;

import com.app.sy.syan.R;
import com.app.sy.syan.data.dto.RPCMessage;
import com.app.sy.syan.data.remote.SyanServiceApi;
import com.app.sy.syan.data.request.NoteBody;
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

public class NotePresenter implements NoteContract.Presenter {
    private static final String TAG = NotePresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private NoteContract.View mView;

    @Inject
    public NotePresenter(Context context, SyanServiceApi syanServiceApir, NoteContract.View view) {
        this.context = context;
        this.mSyanServiceApi = syanServiceApir;
        this.mView = view;
    }

    @Override
    public void getDate(String orderInfo) {
        NoteBody noteBody = new NoteBody(PreferenceUtils.getPrefString(context, Constant.STAFF_NUMBER, ""), orderInfo);
        mSyanServiceApi.note(noteBody)
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
                                mView.noteSuccess();
                            }
                        }
                    }
                });
    }

}
