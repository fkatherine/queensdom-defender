package com.katherinefreeman.queensdomdefender.gamestatus.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.katherinefreeman.queensdomdefender.databinding.FragmentGameStatusBinding;

public class GameStatusFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentGameStatusBinding binding = FragmentGameStatusBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}
