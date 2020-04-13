package com.katherinefreeman.queensdomdefender.playingfield.view;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.katherinefreeman.queensdomdefender.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.fragment.app.testing.FragmentScenario.launchInContainer;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.katherinefreeman.queensdomdefender.espresso.matcher.RecyclerViewMatcher.withItemCount;

@RunWith(AndroidJUnit4.class)
public class PlayingFieldFragmentTest {

    @Test
    public void shouldLoadFiveHeroCharacterCards() {
        launchInContainer(PlayingFieldFragment.class);

        onView(withId(R.id.playing_field_hero_character_card_list))
                .check(matches(withItemCount(5)));
    }

}
