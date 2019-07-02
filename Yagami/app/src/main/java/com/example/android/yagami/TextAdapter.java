package com.example.android.yagami;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextAdapter extends RecyclerView.Adapter {
    private List<String> items = new ArrayList<String>();


    private static View.OnClickListener onItemClickListener;

    public void setItemClickListener(View.OnClickListener clickListener) {
        onItemClickListener = clickListener;
    }

    public void setItems(List<String> itemlist) {
        items = itemlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout, parent, false);
        return new TextViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder)
            ((TextViewHolder) holder).bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public void undoDelete(int deletedItemPos, String deletedItem){
        items.remove(deletedItemPos);
        notifyItemRemoved(deletedItemPos);
        items.add(deletedItemPos,
                deletedItem);
        notifyItemInserted(deletedItemPos);
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;



        public TextViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.description);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void bind(String text) {
            textView.setText(text);
        }
    }
}
