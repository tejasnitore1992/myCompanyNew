package com.mycompanynew.home.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemCurrentOpeningBinding;
import com.mycompanynew.interfaces.ClickListener;

public class CurrentOpeningAdapter extends RecyclerView.Adapter<CurrentOpeningAdapter.MyViewHolder> {

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCurrentOpeningBinding binding = ItemCurrentOpeningBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new CurrentOpeningAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.binding.mbtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null)
                    clickListener.onSelect(null,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemCurrentOpeningBinding binding;

        public MyViewHolder(@NonNull ItemCurrentOpeningBinding binding) {
            super(binding.getRoot());

            this.binding =binding;
        }
    }
}
