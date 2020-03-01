package com.katherinefreeman.queensdomdefender;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.view.View;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasBackground;
import static androidx.test.espresso.matcher.ViewMatchers.hasTextColor;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
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
