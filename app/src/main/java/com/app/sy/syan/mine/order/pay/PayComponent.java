package com.app.sy.syan.mine.order.pay;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = PayModule.class)
public interface PayComponent {
    void inject(PayActivity payActivity);
}
