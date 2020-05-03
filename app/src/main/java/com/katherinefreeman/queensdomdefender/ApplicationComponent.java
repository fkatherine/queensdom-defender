package com.katherinefreeman.queensdomdefender;

import com.katherinefreeman.queensdomdefender.gamelog.view.GameLogFragment;
import com.katherinefreeman.queensdomdefender.herohand.view.HeroHandFragment;
import com.katherinefreeman.queensdomdefender.turnstatus.view.TurnStatusFragment;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(Application application);

    void inject(ApplicationActivity applicationActivity);

    void inject(GameLogFragment gameLogFragment);

    void inject(TurnStatusFragment turnStatusFragment);

    void inject(HeroHandFragment heroHandFragment);

}
