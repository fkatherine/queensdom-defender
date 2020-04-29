package com.katherinefreeman.queensdomdefender;

import com.katherinefreeman.queensdomdefender.gamelog.view.GameLogFragment;
import com.katherinefreeman.queensdomdefender.turnstatus.view.TurnStatusFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(Application application);

    void inject(ApplicationActivity applicationActivity);

    void inject(GameLogFragment gameLogFragment);
    void inject(TurnStatusFragment turnStatusFragment);

}
