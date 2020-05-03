package com.katherinefreeman.queensdomdefender.gamelog.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.ObservableList;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.katherinefreeman.queensdomdefender.Application;
import com.katherinefreeman.queensdomdefender.databinding.FragmentGameLogBinding;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

import javax.inject.Inject;

public class GameLogFragment extends Fragment {

    @Inject
    GameLogFragmentViewModel viewModel;

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

        FragmentGameLogBinding binding = FragmentGameLogBinding.inflate(inflater, container, false);

        configureGameLogItems(binding);
        configureGameLogItemsListener(binding);

        return binding.getRoot();
    }

    private void configureGameLogItems(FragmentGameLogBinding binding) {
        GameLogAdapter gameLogAdapter = new GameLogAdapter(viewModel.gameLogItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.gameLog.setLayoutManager(linearLayoutManager);
        binding.gameLog.setAdapter(gameLogAdapter);
        binding.gameLog.setHasFixedSize(true);
    }

    private void configureGameLogItemsListener(FragmentGameLogBinding binding) {
        viewModel.gameLogItems.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<GameLogItem>>() {
            @Override
            public void onChanged(ObservableList<GameLogItem> sender) {
            }

            @Override
            public void onItemRangeChanged(ObservableList<GameLogItem> sender, int positionStart, int itemCount) {
            }

            @Override
            public void onItemRangeInserted(ObservableList<GameLogItem> sender, int positionStart, int itemCount) {
                binding.gameLog.getAdapter().notifyDataSetChanged();
                binding.gameLog.scrollToPosition(viewModel.gameLogItems.size() - 1);
            }

            @Override
            public void onItemRangeMoved(ObservableList<GameLogItem> sender, int fromPosition, int toPosition, int itemCount) {
            }

            @Override
            public void onItemRangeRemoved(ObservableList<GameLogItem> sender, int positionStart, int itemCount) {
            }
        });
    }

}
