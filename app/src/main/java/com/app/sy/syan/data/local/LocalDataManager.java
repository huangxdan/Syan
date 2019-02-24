package com.app.sy.syan.data.local;

import android.content.Context;

import com.app.sy.syan.SyanApplication;
import com.app.sy.syan.util.CacheUtil;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class LocalDataManager {
    private SyanApplication application;

    @Inject
    public LocalDataManager(SyanApplication application) {
        this.application = application;
    }

//    @Provides
//    @Singleton
//    SyanD provideDataBaseHelper() {
//        return SlopeDataBaseHelper.getInstance(application);
//    }
//
//    @Provides
//    @Singleton
//    DaoSession getDaoSession() {
//        return SlopeDataBaseHelper.getInstance(application).getDaoSession();
//    }

//    @Provides
//    @Singleton
//    CacheUtil getCacheUtil(Context context) {
//        return CacheUtil.getInstance(application, getDaoSession());
//    }
}
