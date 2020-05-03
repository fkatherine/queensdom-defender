package com.katherinefreeman.queensdomdefender.gamelog.view;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.TestApplicationResource;
import com.katherinefreeman.queensdomdefender.event.EventBus;

import org.junit.Rule;
import org.junit.Test;

import javax.inject.Inject;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;


public class GameLogFragmentTest {

    @Rule
    public TestApplicationResource resource = new TestApplicationResource(this);

    @Inject
    EventBus eventBus;

    @Test
    public void shouldAddGameLogItemOnGameLogEvent() {
        launchInContainer(GameLogFragment.class);

        eventBus.logGameEvent("Test game log", R.color.blueBlack);

        await().atMost(5, SECONDS).untilAsserted(() ->
                onView(withId(R.id.game_log_contents))
                        .check(matches(withText("Test game log")))
        );
    }
}