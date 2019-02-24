package com.app.sy.syan.welfare.extract;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;


@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = ExtractModule.class)
public interface ExtractComponent {
    void inject(ExtractActivity activity);
}
