package com.example.blankproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewItemAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    private List<String> preferencesItems;
    public RecyclerViewItemAdapter(List<String> preferencesItems) {
        this.preferencesItems = preferencesItems;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View preferenceView = inflater.inflate(R.layout.recycler_item, parent, false);

        // Return a new holder instance
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(preferenceView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        String preferenceText = preferencesItems.get(position);
        holder.preferenceTextView.setText(preferenceText);
    }

    @Override
    public int getItemCount() {
        return preferencesItems.size();
    }
}
