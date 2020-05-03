package com.katherinefreeman.queensdomdefender;


import com.katherinefreeman.queensdomdefender.gamelog.view.GameLogFragmentTest;
import com.katherinefreeman.queensdomdefender.turnstatus.view.TurnStatusFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

   void inject(GameLogFragmentTest gameLogFragmentTest);
   void inject(TurnStatusFragmentTest turnStatusFragmentTest);

}

