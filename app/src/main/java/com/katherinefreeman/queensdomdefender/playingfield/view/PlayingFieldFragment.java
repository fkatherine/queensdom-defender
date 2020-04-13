package com.katherinefreeman.queensdomdefender.playingfield.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.katherinefreeman.queensdomdefender.databinding.FragmentPlayingFieldBinding;
import com.katherinefreeman.queensdomdefender.playingfield.model.PlayerCardType;

import static com.katherinefreeman.queensdomdefender.playingfield.model.PlayerCardType.HERO;
import static com.katherinefreeman.queensdomdefender.playingfield.model.PlayerCardType.OPPONENT;

public class PlayingFieldFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentPlayingFieldBinding binding = FragmentPlayingFieldBinding.inflate(inflater, container, false);

        configureOpponentCharacterCards(binding);
        configureHeroCharacterCards(binding);

        return binding.getRoot();
    }

    private void configureOpponentCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(OPPONENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldOpponentCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldOpponentCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldOpponentCharacterCardList.setHasFixedSize(true);
    }

    private void configureHeroCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(HERO);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldHeroCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldHeroCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldHeroCharacterCardList.setHasFixedSize(true);
    }
}
