package com.app.sy.syan.mine.order.confirm;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class ConfirmModule {
    ConfirmActivity confirmActivity;

    public ConfirmModule(ConfirmActivity orderActivity) {
        this.confirmActivity = confirmActivity;
    }

    @ActivityScoped
    @Provides
    ConfirmContract.View provide() {
        return confirmActivity;
    }
}
