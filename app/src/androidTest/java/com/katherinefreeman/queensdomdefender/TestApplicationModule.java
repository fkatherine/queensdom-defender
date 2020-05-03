package com.katherinefreeman.queensdomdefender;

import dagger.Module;
import dagger.Provides;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

@Module
public class TestApplicationModule {

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
