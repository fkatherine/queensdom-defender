package com.katherinefreeman.queensdomdefender.playingfield.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.player.model.PlayerType;

import static com.katherinefreeman.queensdomdefender.player.model.PlayerType.OPPONENT;

class BuildingCardAdapter extends RecyclerView.Adapter<BuildingCardAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private PlayerType type;

    public BuildingCardAdapter(PlayerType cardType) {
        this.type = cardType;
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
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 2;
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
