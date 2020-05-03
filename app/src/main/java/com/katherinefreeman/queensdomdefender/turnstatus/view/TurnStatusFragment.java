package com.katherinefreeman.queensdomdefender.turnstatus.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.fragment.app.Fragment;

import com.katherinefreeman.queensdomdefender.Application;
import com.katherinefreeman.queensdomdefender.databinding.FragmentTurnStatusBinding;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class TurnStatusFragment extends Fragment {

    @Inject
    TurnStatusFragmentViewModel viewModel;

    @BindingAdapter("android:visibility")
    public static void setVisibility(View view, Boolean visible) {
        int visibility = visible ? VISIBLE : GONE;
        view.setVisibility(visibility);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        injectFragmentDependencies(context);

        super.onAttach(context);
    }

    private void injectFragmentDependencies(Context context) {
        ((Application) context.getApplicationContext())
                .getApplicationComponent()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentTurnStatusBinding binding = FragmentTurnStatusBinding.inflate(inflater, container, false);

        binding.setViewModel(viewModel);

        return binding.getRoot();
    }

}
