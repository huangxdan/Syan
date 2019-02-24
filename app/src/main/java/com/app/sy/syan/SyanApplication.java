package com.app.sy.syan;

import android.app.Application;
import android.content.Context;

import com.app.sy.syan.data.local.LocalDataManager;
import com.app.sy.syan.data.remote.RemoteDataManager;

public class SyanApplication extends Application {
    SyanApplication mSyanApplication;
    private ApplicationComponent mAppComponent;

    public static SyanApplication get(Context context) {
        return (SyanApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .remoteDataManager(new RemoteDataManager(this))
                .localDataManager(new LocalDataManager(this))
                .build();
    }

    public SyanApplication getSyanApplication() {
        return mSyanApplication;
    }

    public ApplicationComponent getAppComponent() {
        return mAppComponent;
    }
}
