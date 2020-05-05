package com.katherinefreeman.queensdomdefender.card.view;

import androidx.databinding.ObservableList;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.katherinefreeman.queensdomdefender.card.model.Card;

public class ObservableCardListCallbackListener extends ObservableList.OnListChangedCallback<ObservableList<Card>> {

    private Adapter adapter;

    public ObservableCardListCallbackListener(Adapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onChanged(ObservableList<Card> sender) {
    }

    @Override
    public void onItemRangeChanged(ObservableList<Card> sender, int positionStart, int itemCount) {
    }

    @Override
    public void onItemRangeInserted(ObservableList<Card> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeInserted(positionStart, itemCount);
    }

    @Override
    public void onItemRangeMoved(ObservableList<Card> sender, int fromPosition, int toPosition, int itemCount) {
    }

    @Override
    public void onItemRangeRemoved(ObservableList<Card> sender, int positionStart, int itemCount) {
        adapter.notifyItemRangeRemoved(positionStart, itemCount);
    }

}
