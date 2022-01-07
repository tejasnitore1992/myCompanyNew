package com.mycompanynew.our_services.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemServicesBinding;
import com.mycompanynew.interfaces.ClickListener;

public class OurServicesAdapter extends RecyclerView.Adapter<OurServicesAdapter.MyViewHolder> {

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemServicesBinding binding = ItemServicesBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_services,parent,false);
        return new OurServicesAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.binding.llcService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null)
                    clickListener.onSelect(null,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ItemServicesBinding binding;
        public MyViewHolder(@NonNull ItemServicesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
