package com.katherinefreeman.queensdomdefender;

import android.content.Context;
import android.content.res.Configuration;

import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.katherinefreeman.queensdomdefender.playingfield.view.PlayingFieldFragment;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class ApplicationActivityTest {

    @Rule
    public ActivityTestRule<ApplicationActivity> activityRule =
            new ActivityTestRule<>(ApplicationActivity.class);

    @Test
    public void shouldLaunchActivityInLandscapeMode() {
        int activityOrientation = withCurrentApplicationContext()
                .getResources()
                .getConfiguration()
                .orientation;
        assertThat(activityOrientation, is(Configuration.ORIENTATION_LANDSCAPE));
    }

    @Test
    public void shouldShowGameBoard() {
        onView(withId(R.id.game_board))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowPlayingField() {
        onView(withId(R.id.playing_field))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldLoadPlayingFieldFragmentForPlayingFieldView() {
        Fragment fragment = withCurrentActivity()
                .getSupportFragmentManager()
                .findFragmentById(R.id.playing_field);

        assertThat(fragment, instanceOf(PlayingFieldFragment.class));
    }

    @Test
    public void shouldShowEndTurn() {
        onView(withId(R.id.turn_status))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowGameStatusTracker() {
        onView(withId(R.id.game_status_tracker))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowHeroHand() {
        onView(withId(R.id.hero_hand))
                .check(matches(isDisplayed()));
    }

    private ApplicationActivity withCurrentActivity() {
        return activityRule.getActivity();
    }

    private Context withCurrentApplicationContext() {
        return withCurrentActivity().getApplicationContext();
    }

}
