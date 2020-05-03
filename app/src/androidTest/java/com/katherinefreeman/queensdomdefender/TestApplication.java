package com.katherinefreeman.queensdomdefender;

public class TestApplication extends Application {

    @Override
    protected void initialiseDagger() {
        applicationComponent = DaggerTestApplicationComponent
                .builder()
                .testApplicationModule(new TestApplicationModule())
                .build();
        applicationComponent.inject(this);
    }

}
