package com.app.sy.syan.mine.car;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;

@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = CarModule.class)
public interface CarComponent {
    void inject(CarActivity carActivity);
}
