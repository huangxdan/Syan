package com.app.sy.syan.note;

import com.app.sy.syan.ApplicationComponent;
import com.app.sy.syan.scope.FragmentScoped;

import dagger.Component;

@FragmentScoped
@Component(dependencies = ApplicationComponent.class, modules = NoteModule.class)
public interface NoteComponent {
    void inject(NoteFragment noteFragment);
}
