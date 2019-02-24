package com.app.sy.syan.note;

import com.app.sy.syan.scope.FragmentScoped;

import dagger.Module;
import dagger.Provides;

@Module
public class NoteModule {
    NoteFragment noteFragment;

    public NoteModule(NoteFragment noteFragment) {
        this.noteFragment = noteFragment;
    }

    @FragmentScoped
    @Provides
    NoteContract.View provideView() {
        return noteFragment;
    }
}
