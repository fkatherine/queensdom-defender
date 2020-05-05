package com.katherinefreeman.queensdomdefender.playingfield.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.card.model.Card;
import com.katherinefreeman.queensdomdefender.databinding.LayoutPlayingFieldBuildingCardBinding;
import com.katherinefreeman.queensdomdefender.databinding.LayoutPlayingFieldOpponentBuildingCardBinding;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import java.util.List;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

class BuildingCardAdapter extends RecyclerView.Adapter<BuildingCardAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private PlayerType type;
    private List<Card> cards;


    public BuildingCardAdapter(PlayerType cardType, List<Card> cards) {
        this.type = cardType;
        this.cards = cards;
    }

    @NonNull
    @Override
    public BuildingCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = getBuildingCardLayout(parent);
        return new ViewHolder(binding);
    }

    private ViewDataBinding getBuildingCardLayout(ViewGroup parent) {
        if (type == OPPONENT) {
            return DataBindingUtil.inflate(layoutInflater, R.layout.layout_playing_field_opponent_building_card, parent, false);
        }
        return DataBindingUtil.inflate(layoutInflater, R.layout.layout_playing_field_building_card, parent, false);

    }

    @Override
    public void onBindViewHolder(@NonNull BuildingCardAdapter.ViewHolder holder, int position) {
        Card card = cards.get(position);
        if (type == OPPONENT) {
            LayoutPlayingFieldOpponentBuildingCardBinding binding = (LayoutPlayingFieldOpponentBuildingCardBinding) holder.getBinding();
            binding.setCard(card);
        } else {
            LayoutPlayingFieldBuildingCardBinding binding = (LayoutPlayingFieldBuildingCardBinding) holder.getBinding();
            binding.setCard(card);
        }

        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

    }

}
