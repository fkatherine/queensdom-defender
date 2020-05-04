package com.katherinefreeman.queensdomdefender.view.binding;

import android.view.View;

import androidx.databinding.BindingAdapter;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class BindingAdapters {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean visible) {
        int visibility = visible ? VISIBLE : GONE;
        view.setVisibility(visibility);
    }

}
