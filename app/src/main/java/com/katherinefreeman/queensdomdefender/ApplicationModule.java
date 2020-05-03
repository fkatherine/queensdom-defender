package com.katherinefreeman.queensdomdefender;

import dagger.Module;
import dagger.Provides;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Singleton;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    public EventBus provideEventBus() {
        return EventBus.getDefault();
    }

}
