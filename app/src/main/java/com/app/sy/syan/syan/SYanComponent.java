package com.app.sy.syan.syan;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = SYanModule.class)
public interface SYanComponent {
    void inject(SYanActivity activity);
}
