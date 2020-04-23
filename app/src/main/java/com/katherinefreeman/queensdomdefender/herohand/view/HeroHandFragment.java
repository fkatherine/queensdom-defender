package com.katherinefreeman.queensdomdefender.herohand.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.katherinefreeman.queensdomdefender.databinding.FragmentHeroHandBinding;

public class HeroHandFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        FragmentHeroHandBinding binding = FragmentHeroHandBinding.inflate(inflater, container, false);

        configureHeroHandCards(binding);

        return binding.getRoot();
    }

    private void configureHeroHandCards(FragmentHeroHandBinding binding) {
        HeroHandAdapter heroHandAdapter = new HeroHandAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.heroHandCardList.setLayoutManager(linearLayoutManager);
        binding.heroHandCardList.setAdapter(heroHandAdapter);
        binding.heroHandCardList.setHasFixedSize(true);
    }
}
