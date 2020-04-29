package com.katherinefreeman.queensdomdefender;


import com.katherinefreeman.queensdomdefender.gamelog.view.GameLogFragmentTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestApplicationModule.class})
public interface TestApplicationComponent extends ApplicationComponent {

   void inject(GameLogFragmentTest gameLogFragmentTest);

}

