package com.app.sy.syan.syan;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class SYanModule {
    SYanActivity activity;

    public SYanModule(SYanActivity activity) {
        this.activity = activity;
    }

    @ActivityScoped
    @Provides
    SYanContract.View provideView() {
        return activity;
    }
}
