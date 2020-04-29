package com.katherinefreeman.queensdomdefender;

import androidx.test.platform.app.InstrumentationRegistry;
import com.katherinefreeman.queensdomdefender.gamelog.view.GameLogFragmentTest;
import org.junit.rules.ExternalResource;

public class TestApplicationResource extends ExternalResource {

    private Object testClass;

    public TestApplicationResource(Object testClass) {
        this.testClass = testClass;
    }

    @Override
    protected void before() throws Throwable {
        TestApplication testApplication = (TestApplication) (InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext()
                .getApplicationContext());
        TestApplicationComponent testApplicationComponent = (TestApplicationComponent) testApplication
                .getApplicationComponent();

        injectDependencies(testClass, testApplicationComponent);
    }

    private void injectDependencies(Object testClass, TestApplicationComponent testApplicationComponent) {
        if (testClass instanceof GameLogFragmentTest) {
            testApplicationComponent.inject((GameLogFragmentTest) testClass);
        }
    }
}
