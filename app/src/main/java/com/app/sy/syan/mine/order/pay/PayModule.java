package com.app.sy.syan.mine.order.pay;

import com.app.sy.syan.mine.order.confirm.ConfirmContract;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class PayModule {
    PayActivity payActivity;

    public PayModule(PayActivity payActivity) {
        this.payActivity = payActivity;
    }

    @ActivityScoped
    @Provides
    PayContract.View provide() {
        return payActivity;
    }

}
