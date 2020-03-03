package com.katherinefreeman.queensdomdefender;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.katherinefreeman.queensdomdefender.playingfield.view.PlayingFieldFragment;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void shouldLaunchActivityInLandscapeMode() {
        int activityOrientation = withCurrentApplicationContext().getResources().getConfiguration().orientation;
        assertThat(activityOrientation, is(Configuration.ORIENTATION_LANDSCAPE));
    }

    @Test
    public void shouldShowGameBoard() {
        onView(withId(R.id.game_board))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveBlackBackgroundForGameBoard() {
        assertThatViewHasBackgroundColour(R.id.game_board, R.color.black);
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
    public void shouldHaveRedBackgroundForPlayingField() {
        assertThatViewHasBackgroundColour(R.id.playing_field, R.color.red);
    }

    @Test
    public void shouldHaveMinimumDimensionsForPlayingField() {
        int height = withCurrentActivity().findViewById(R.id.playing_field).getHeight();
        int width = withCurrentActivity().findViewById(R.id.playing_field).getWidth();

        assertThat(height, is(525));
        assertThat(width, is(1050));
    }

    @Test
    public void shouldShowEndTurn() {
        onView(withId(R.id.end_turn))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveBlueBackgroundForEndTurn() {
        assertThatViewHasBackgroundColour(R.id.end_turn, R.color.blue);
    }

    @Test
    public void shouldHaveMinimumDimensionsForEndTurn() {
        int height = withCurrentActivity().findViewById(R.id.end_turn).getHeight();
        int width = withCurrentActivity().findViewById(R.id.end_turn).getWidth();

        assertThat(height, is(262));
        assertThat(width, is(262));
    }

    @Test
    public void shouldShowGameStatusTracker() {
        onView(withId(R.id.game_status_tracker))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveGreenBackgroundForGameStatusTracker() {
        assertThatViewHasBackgroundColour(R.id.game_status_tracker, R.color.green);
    }

    @Test
    public void shouldHaveMinimumDimensionsForGameStatusTracker() {
        int height = withCurrentActivity().findViewById(R.id.game_status_tracker).getHeight();
        int width = withCurrentActivity().findViewById(R.id.game_status_tracker).getWidth();

        assertThat(height, is(525));
        assertThat(width, is(262));
    }

    @Test
    public void shouldShowHeroHand() {
        onView(withId(R.id.hero_hand))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldHaveYellowBackgroundForHeroHand() {
        assertThatViewHasBackgroundColour(R.id.hero_hand, R.color.yellow);
    }

    @Test
    public void shouldHaveMinimumDimensionsForHeroHand() {
        int height = withCurrentActivity().findViewById(R.id.hero_hand).getHeight();

        assertThat(height, is(262));
    }

    private MainActivity withCurrentActivity() {
        return activityRule.getActivity();
    }

    private Context withCurrentApplicationContext() {
        return withCurrentActivity().getApplicationContext();
    }

    private void assertThatViewHasBackgroundColour(int view, int expectedColour) {
        View gameBoard = withCurrentActivity().findViewById(view);

        ColorDrawable gameBoardBackground = (ColorDrawable) gameBoard.getBackground();
        int expectedColourResource = withCurrentApplicationContext().getColor(expectedColour);

        assertThat(gameBoardBackground.getColor(), is(expectedColourResource));
    }
}
