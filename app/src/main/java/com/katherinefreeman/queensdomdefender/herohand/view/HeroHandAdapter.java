package com.katherinefreeman.queensdomdefender.herohand.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.databinding.LayoutHeroHandCardBinding;
import com.katherinefreeman.queensdomdefender.event.EventBus;

import java.util.List;

public class HeroHandAdapter extends RecyclerView.Adapter<HeroHandAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private EventBus eventBus;
    private List<Card> hand;

    public HeroHandAdapter(EventBus eventBus, List<Card> hand) {
        this.eventBus = eventBus;
        this.hand = hand;
    }

    @NonNull
    @Override
    public HeroHandAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LayoutHeroHandCardBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_hero_hand_card, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroHandAdapter.ViewHolder holder, int position) {
        Card card = hand.get(position);
        HeroHandCardViewModel viewModel = new HeroHandCardViewModel(card, eventBus);
        holder.getBinding().setViewModel(viewModel);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return hand.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutHeroHandCardBinding binding;

        public ViewHolder(LayoutHeroHandCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public LayoutHeroHandCardBinding getBinding() {
            return binding;
        }

    }

}
