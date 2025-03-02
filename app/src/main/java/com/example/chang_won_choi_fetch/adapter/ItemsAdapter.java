package com.example.chang_won_choi_fetch.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.chang_won_choi_fetch.Item;
import com.example.chang_won_choi_fetch.R;

import java.util.List;

/***
 * Adapter to handle displaying the item list.
 * Each row shows the listId and name of the item.
 ***/
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>{
    private List<Item> itemList;

    public ItemsAdapter(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void updateItems(List<Item> newItemList) {
        this.itemList = newItemList;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.listIdTextView.setText(String.valueOf(item.getListId()));
        holder.nameTextView.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    /***
     * Custom ViewHolder for each of item view.
     ***/
    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView listIdTextView;
        TextView nameTextView;

        ItemViewHolder(View itemView) {
            super(itemView);
            listIdTextView = itemView.findViewById(R.id.listIdText);
            nameTextView = itemView.findViewById(R.id.nameText);
        }
    }
}
