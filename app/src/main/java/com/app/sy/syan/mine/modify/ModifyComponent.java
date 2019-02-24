package com.app.sy.syan.mine.modify;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;


@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ModifyModule.class)
public interface ModifyComponent {
    void inject(ModifyActivity modifyActivity);
}
