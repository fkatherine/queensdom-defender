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

public class CharacterCardAdapter extends RecyclerView.Adapter<CharacterCardAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private PlayerType type;

    public CharacterCardAdapter(PlayerType type) {
        this.type = type;
    }

    @NonNull
    @Override
    public CharacterCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ViewDataBinding binding = getCharacterCardLayout(parent);
        return new ViewHolder(binding);
    }

    private ViewDataBinding getCharacterCardLayout(@NonNull ViewGroup parent) {
        if (type == OPPONENT) {
            return DataBindingUtil.inflate(layoutInflater, R.layout.layout_playing_field_opponent_character_card, parent, false);
        }
        return DataBindingUtil.inflate(layoutInflater, R.layout.layout_playing_field_character_card, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterCardAdapter.ViewHolder holder, int position) {
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return 5;
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
