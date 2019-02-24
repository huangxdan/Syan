package com.app.sy.syan.welfare.extract;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;


@Module
public class ExtractModule {
    ExtractActivity extractActivity;

    public ExtractModule(ExtractActivity extractActivity) {
        this.extractActivity = extractActivity;
    }

    @ActivityScoped
    @Provides
    ExtractContract.View provideView(){return extractActivity;}
}
