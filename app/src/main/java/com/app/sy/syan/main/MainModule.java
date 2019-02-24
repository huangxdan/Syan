package com.app.sy.syan.main;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {
    private MainActivity mainActivity;

    public MainModule(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    @ActivityScoped
    @Provides
    MainContract.View provideView() {
        return mainActivity;
    }
}
