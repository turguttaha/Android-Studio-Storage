package com.example.blankproject;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    // Your holder should contain a member variable
    // for any view that will be set as you render a row
    public TextView preferenceTextView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        this.preferenceTextView = itemView.findViewById(R.id.preference_item);
    }
}
