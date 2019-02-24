package com.app.sy.syan.love;

import com.app.sy.syan.scope.FragmentScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class LoveModule {
    LoveFragment loveFragment;

    public LoveModule(LoveFragment loveFragment) {
        this.loveFragment = loveFragment;
    }

    @FragmentScoped
    @Provides
    LoveContract.View provideView() {
        return loveFragment;
    }
}
