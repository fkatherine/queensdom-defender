package com.katherinefreeman.queensdomdefender.espresso.matcher;

import android.view.View;
import android.widget.ListView;

import androidx.recyclerview.widget.RecyclerView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class RecyclerViewMatcher {
    public static Matcher<View> withItemCount(final int count) {
        return new TypeSafeMatcher<View>() {
            @Override public boolean matchesSafely (final View view) {
                RecyclerView recyclerView = (RecyclerView) view;
                RecyclerView.Adapter adapter = recyclerView.getAdapter();
                return adapter.getItemCount() == count;
            }

            @Override public void describeTo (final Description description) {
                description.appendText ("RecyclerView should have " + count + " items");
            }
        };
    }
}
