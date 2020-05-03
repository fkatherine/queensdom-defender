package com.katherinefreeman.queensdomdefender;

public class Application extends android.app.Application {

    protected ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        initialiseDagger();
    }

    protected void initialiseDagger() {
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule())
                .build();
        applicationComponent.inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

}
