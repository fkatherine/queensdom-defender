package com.katherinefreeman.queensdomdefender.turnstatus.view;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.katherinefreeman.queensdomdefender.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class TurnStatusFragmentTest {

    @Test
    public void shouldShowPlayerTurnStatusWhenCurrentPlayerTypeIsHero() {
        launchInContainer(TurnStatusFragment.class);

        onView(withId(R.id.hero_turn_status)).check(matches(isDisplayed()));
        onView(withId(R.id.hero_end_turn_button)).check(matches(isDisplayed()));
    }
}