package com.katherinefreeman.queensdomdefender.gamelog.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.databinding.FragmentGameLogBinding;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import java.util.Arrays;
import java.util.List;

public class GameLogFragment extends Fragment {

    private List<GameLogItem> gameLogItems = Arrays.asList(
            new GameLogItem("Opponent has begun turn", R.color.black),
            new GameLogItem("Hero has begun turn", R.color.black),
            new GameLogItem("Opponent has ended turn", R.color.black),
            new GameLogItem("You cannot attack Character cards when there are Building cards on Playing Field", R.color.red)
    );

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentGameLogBinding binding = FragmentGameLogBinding.inflate(inflater, container, false);

        configureGameLogItems(binding);

        return binding.getRoot();
    }

    private void configureGameLogItems(FragmentGameLogBinding binding) {
        GameLogAdapter gameLogAdapter = new GameLogAdapter(gameLogItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.gameLog.setLayoutManager(linearLayoutManager);
        binding.gameLog.setAdapter(gameLogAdapter);
        binding.gameLog.setHasFixedSize(true);
    }
}
