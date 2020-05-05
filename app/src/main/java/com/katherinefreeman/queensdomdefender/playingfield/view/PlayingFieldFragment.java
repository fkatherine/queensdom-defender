package com.katherinefreeman.queensdomdefender.playingfield.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.katherinefreeman.queensdomdefender.Application;
import com.katherinefreeman.queensdomdefender.card.view.ObservableCardListCallbackListener;
import com.katherinefreeman.queensdomdefender.databinding.FragmentPlayingFieldBinding;
import com.katherinefreeman.queensdomdefender.event.EventBus;

import javax.inject.Inject;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.HERO;
import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

public class PlayingFieldFragment extends Fragment {

    @Inject
    PlayingFieldFragmentViewModel viewModel;

    @Inject
    EventBus eventBus;

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
        FragmentPlayingFieldBinding binding = FragmentPlayingFieldBinding.inflate(inflater, container, false);

        configureOpponentBuildingCards(binding);
        configureOpponentBuildingCardsListener(binding);

        configureOpponentCharacterCards(binding);
        configureOpponentCharacterCardsListener(binding);

        configureHeroBuildingCards(binding);
        configureHeroBuildingCardsListener(binding);

        configureHeroCharacterCards(binding);
        configureHeroCharacterCardsListener(binding);

        return binding.getRoot();
    }

    private void configureOpponentBuildingCards(FragmentPlayingFieldBinding binding) {
        BuildingCardAdapter buildingCardAdapter = new BuildingCardAdapter(OPPONENT, viewModel.opponentBuildingCards);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.playingFieldOpponentBuildingCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldOpponentBuildingCardList.setAdapter(buildingCardAdapter);
        binding.playingFieldOpponentBuildingCardList.setHasFixedSize(true);
    }

    private void configureOpponentBuildingCardsListener(FragmentPlayingFieldBinding binding) {
        RecyclerView.Adapter adapter = binding.playingFieldOpponentBuildingCardList.getAdapter();
        viewModel.opponentBuildingCards.addOnListChangedCallback(new ObservableCardListCallbackListener(adapter));
    }

    private void configureOpponentCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(OPPONENT, viewModel.opponentCharacterCards, eventBus);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldOpponentCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldOpponentCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldOpponentCharacterCardList.setHasFixedSize(true);
    }

    private void configureOpponentCharacterCardsListener(FragmentPlayingFieldBinding binding) {
        RecyclerView.Adapter adapter = binding.playingFieldOpponentCharacterCardList.getAdapter();
        viewModel.opponentCharacterCards.addOnListChangedCallback(new ObservableCardListCallbackListener(adapter));
    }

    private void configureHeroBuildingCards(FragmentPlayingFieldBinding binding) {
        BuildingCardAdapter buildingCardAdapter = new BuildingCardAdapter(HERO, viewModel.userBuildingCards);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        binding.playingFieldHeroBuildingCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldHeroBuildingCardList.setAdapter(buildingCardAdapter);
        binding.playingFieldHeroBuildingCardList.setHasFixedSize(true);
    }

    private void configureHeroBuildingCardsListener(FragmentPlayingFieldBinding binding) {
        RecyclerView.Adapter adapter = binding.playingFieldHeroBuildingCardList.getAdapter();
        viewModel.userBuildingCards.addOnListChangedCallback(new ObservableCardListCallbackListener(adapter));
    }

    private void configureHeroCharacterCards(FragmentPlayingFieldBinding binding) {
        CharacterCardAdapter characterCardAdapter = new CharacterCardAdapter(HERO, viewModel.userCharacterCards, eventBus);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.playingFieldHeroCharacterCardList.setLayoutManager(linearLayoutManager);
        binding.playingFieldHeroCharacterCardList.setAdapter(characterCardAdapter);
        binding.playingFieldHeroCharacterCardList.setHasFixedSize(true);
    }

    private void configureHeroCharacterCardsListener(FragmentPlayingFieldBinding binding) {
        RecyclerView.Adapter adapter = binding.playingFieldHeroCharacterCardList.getAdapter();
        viewModel.userCharacterCards.addOnListChangedCallback(new ObservableCardListCallbackListener(adapter));
    }

}
