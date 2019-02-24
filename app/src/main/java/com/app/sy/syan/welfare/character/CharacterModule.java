package com.app.sy.syan.welfare.character;

import com.app.sy.syan.scope.ActivityScoped;

import dagger.Module;
import dagger.Provides;


@Module
public class CharacterModule {
    CharacterActivity characterActivity;

    public CharacterModule(CharacterActivity characterActivity) {
        this.characterActivity = characterActivity;
    }

    @ActivityScoped
    @Provides
    CharacterContract.View provideView() {
        return characterActivity;
    }
}
