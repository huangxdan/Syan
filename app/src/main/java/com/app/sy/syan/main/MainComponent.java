package com.app.sy.syan.main;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = MainModule.class)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
