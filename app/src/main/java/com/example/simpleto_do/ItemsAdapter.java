package com.example.simpleto_do;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

// Takes data from the model and displays it in the Recycler View
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.viewHolder> {

    public interface OnLongClickListener {

        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener) {

        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Use layout inflator to inflate view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        // Wrap inside a View Holder and return
        return new viewHolder(todoView);
    }

    // Binds data to a particular View Holder
    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        // Grab item at position
        String item = items.get(position);

        // Bind item into View Holder
        holder.bind(item);

    }

    // Tells RV # of items in list
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Container that provides easy access to views that represents each row of the list
    class viewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // Update view inside of the View Holder
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    // Notify listener of long clicked item
                    longClickListener.onItemLongClicked(getAdapterPosition());

                    return true;
                }
            });

        }
    }

}
