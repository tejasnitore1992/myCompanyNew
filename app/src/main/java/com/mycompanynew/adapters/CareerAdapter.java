package com.mycompanynew.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.databinding.ItemCareerBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningItem;

import java.util.List;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.MyViewHolder> {

    private Context context;
    public List<CurrentOpeningItem> itemList;

    private ClickListener clickListener;

    public CareerAdapter(Context context, List<CurrentOpeningItem> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCareerBinding binding = ItemCareerBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new CareerAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CurrentOpeningItem openingItem = itemList.get(position);
        holder.binding.mtvTitle.setText(openingItem.getCompanyCurrentOpeningTitle());
        holder.binding.mtvTime.setText(String.format("  %s",openingItem.getCompanyCurrentOpeningTiming()));
        holder.binding.mtvLocation.setText(String.format("  %s",openingItem.getCompanyCurrentOpeningAddress()));
        holder.binding.mbtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null)
                    clickListener.onSelect(openingItem,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ItemCareerBinding binding;


        public MyViewHolder(ItemCareerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
