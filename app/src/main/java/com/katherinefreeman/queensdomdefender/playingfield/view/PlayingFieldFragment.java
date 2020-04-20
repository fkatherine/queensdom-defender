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

        configureOpponentBuildingCards(binding);
        configureOpponentCharacterCards(binding);
        configureHeroCharacterCards(binding);
        configureHeroBuildingCards(binding);

        return binding.getRoot();
    }

    private void configureOpponentBuildingCards(FragmentPlayingFieldBinding binding) {
        BuildingCardAdapter buildingCardAdapter = new BuildingCardAdapter(OPPONENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.playingFieldOpponentBuildingCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldOpponentBuildingCardList.setAdapter(buildingCardAdapter);
        binding.playingFieldOpponentBuildingCardList.setHasFixedSize(true);
    }

    private void configureOpponentCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(OPPONENT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldOpponentCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldOpponentCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldOpponentCharacterCardList.setHasFixedSize(true);
    }

    private void configureHeroBuildingCards(FragmentPlayingFieldBinding binding) {
        BuildingCardAdapter buildingCardAdapter = new BuildingCardAdapter(HERO);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.playingFieldHeroBuildingCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldHeroBuildingCardList.setAdapter(buildingCardAdapter);
        binding.playingFieldHeroBuildingCardList.setHasFixedSize(true);
    }

    private void configureHeroCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(HERO);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldHeroCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldHeroCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldHeroCharacterCardList.setHasFixedSize(true);
    }


}
