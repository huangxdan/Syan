package com.app.sy.syan;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public final class ApplicationModule {

    private final SyanApplication mContext;

    public ApplicationModule(SyanApplication context) {
        mContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return mContext.getApplicationContext();
    }

}