package com.katherinefreeman.queensdomdefender.herohand.view;

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
import androidx.recyclerview.widget.RecyclerView.LayoutParams;

import com.katherinefreeman.queensdomdefender.Application;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.databinding.FragmentHeroHandBinding;
import com.katherinefreeman.queensdomdefender.event.EventBus;

import javax.inject.Inject;

public class HeroHandFragment extends Fragment {

    @Inject
    HeroHandFragmentViewModel viewModel;

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
        FragmentHeroHandBinding binding = FragmentHeroHandBinding.inflate(inflater, container, false);

        configureHeroHandCards(binding);
        configureHandListener(binding);

        return binding.getRoot();
    }

    private void configureHeroHandCards(FragmentHeroHandBinding binding) {
        HeroHandAdapter heroHandAdapter = new HeroHandAdapter(eventBus, viewModel.hand);
        LinearLayoutManager linearLayoutManager = getConfiguredHeroHandCardLayout();
        binding.heroHandCardList.setLayoutManager(linearLayoutManager);
        binding.heroHandCardList.setAdapter(heroHandAdapter);
        binding.heroHandCardList.setHasFixedSize(true);
    }

    private LinearLayoutManager getConfiguredHeroHandCardLayout() {
        return new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false) {
            @Override
            public boolean checkLayoutParams(LayoutParams layoutParams) {
                layoutParams.width = getWidth() / 8;
                return true;
            }
        };
    }

    private void configureHandListener(FragmentHeroHandBinding binding) {
        viewModel.hand.addOnListChangedCallback(new ObservableList.OnListChangedCallback<ObservableList<Card>>() {
            @Override
            public void onChanged(ObservableList<Card> sender) {
            }

            @Override
            public void onItemRangeChanged(ObservableList<Card> sender, int positionStart, int itemCount) {
            }

            @Override
            public void onItemRangeInserted(ObservableList<Card> sender, int positionStart, int itemCount) {
                binding.heroHandCardList.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onItemRangeMoved(ObservableList<Card> sender, int fromPosition, int toPosition, int itemCount) {
            }

            @Override
            public void onItemRangeRemoved(ObservableList<Card> sender, int positionStart, int itemCount) {
                binding.heroHandCardList.getAdapter().notifyDataSetChanged();
            }
        });
    }

}
