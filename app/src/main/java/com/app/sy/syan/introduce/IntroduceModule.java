package com.app.sy.syan.introduce;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class IntroduceModule {
    IntroduceActivity activity;

    public IntroduceModule(IntroduceActivity activity) {
        this.activity = activity;
    }

    @ActivityScoped
    @Provides
    IntroduceContract.View provideView() {
        return activity;
    }
}
