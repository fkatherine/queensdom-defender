package com.katherinefreeman.queensdomdefender.turnstatus.view;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.TestApplicationResource;
import com.katherinefreeman.queensdomdefender.event.EventBus;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

@RunWith(AndroidJUnit4.class)
public class TurnStatusFragmentTest {

    @Rule
    public TestApplicationResource resource = new TestApplicationResource(this);

    @Inject
    EventBus eventBus;

    @Test
    public void shouldShowPlayerTurnStatusWhenCurrentPlayerTypeIsHeroOnFragmentShown() {
        launchInContainer(TurnStatusFragment.class);

        assertHeroTurnStatusIsDisplayed();
    }


    @Test
    public void shouldShowPlayerTurnStatusWhenNewPlayerIsHeroOnNewTurnStartedEvent() {
        launchInContainer(TurnStatusFragment.class);

        eventBus.newTurnStarted(HERO);

        assertHeroTurnStatusIsDisplayed();
    }

    @Test
    public void shouldShowOpponentTurnStatusWhenNewPlayerIsOpponentOnNewTurnStartedEvent() {
        launchInContainer(TurnStatusFragment.class);

        eventBus.newTurnStarted(OPPONENT);

        onView(withId(R.id.opponent_turn_status)).check(matches(isDisplayed()));
    }

    private void assertHeroTurnStatusIsDisplayed() {
        onView(withId(R.id.hero_turn_status)).check(matches(isDisplayed()));
        onView(withId(R.id.hero_end_turn_button)).check(matches(isDisplayed()));
    }
}