package com.app.sy.syan.mine.order.confirm;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ConfirmModule.class)
public interface ConfirmComponent {
    void inject(ConfirmActivity confirmActivity);
}
