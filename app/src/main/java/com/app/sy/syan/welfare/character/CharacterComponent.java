package com.app.sy.syan.welfare.character;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.ActivityScoped;

import dagger.Component;


@ActivityScoped
@Component(dependencies = ApplicationComponent.class, modules = CharacterModule.class)
public interface CharacterComponent {
    void inject(CharacterActivity activity);
}
