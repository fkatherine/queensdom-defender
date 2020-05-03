package com.katherinefreeman.queensdomdefender.gamelog.view;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.katherinefreeman.queensdomdefender.R;
import com.katherinefreeman.queensdomdefender.databinding.LayoutGameLogItemBinding;
import com.katherinefreeman.queensdomdefender.gamelog.model.GameLogItem;

public class GameLogAdapter extends RecyclerView.Adapter<GameLogAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ObservableArrayList<GameLogItem> logItems;

    public GameLogAdapter(ObservableArrayList<GameLogItem> logItems) {
        this.logItems = logItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        LayoutGameLogItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.layout_game_log_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GameLogItem logItem = logItems.get(position);
        holder.getBinding().setLogItem(logItem);
    }

    @Override
    public int getItemCount() {
        return logItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private LayoutGameLogItemBinding binding;

        ViewHolder(LayoutGameLogItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public LayoutGameLogItemBinding getBinding() {
            return binding;
        }

    }

}
