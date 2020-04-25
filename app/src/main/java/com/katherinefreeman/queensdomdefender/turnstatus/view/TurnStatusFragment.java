package com.katherinefreeman.queensdomdefender.turnstatus.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.katherinefreeman.queensdomdefender.databinding.FragmentTurnStatusBinding;
import com.katherinefreeman.queensdomdefender.turnstatus.model.TurnStatusFragmentViewModel;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TurnStatusFragment extends Fragment {

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean visible) {
        int visibility = visible ? VISIBLE : GONE;
        view.setVisibility(visibility);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {

        FragmentTurnStatusBinding binding = FragmentTurnStatusBinding.inflate(inflater, container, false);

        final TurnStatusFragmentViewModel viewModel = new ViewModelProvider(this).get(TurnStatusFragmentViewModel.class);
        binding.setViewModel(viewModel);

        return binding.getRoot();
    }
}
