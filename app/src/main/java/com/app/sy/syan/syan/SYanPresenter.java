package com.app.sy.syan.syan;

import android.content.Context;

import com.app.sy.syan.data.remote.SyanServiceApi;

import javax.inject.Inject;

public class SYanPresenter implements SYanContract.Presenter{
    private static final String TAG = SYanPresenter.class.getSimpleName();
    private Context context;
    private SyanServiceApi mSyanServiceApi;
    private SYanContract.View mView;

    @Inject
    public SYanPresenter(Context context, SyanServiceApi mSyanServiceApi, SYanContract.View mView) {
        this.context = context;
        this.mSyanServiceApi = mSyanServiceApi;
        this.mView = mView;
    }

    @Override
    public void getData() {

    }
}
