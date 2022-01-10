package com.mycompanynew.our_services.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemBusinessAdvisorBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.our_services.response.DataDetailsItem;

import java.util.List;

public class ServiceImageAdapter extends RecyclerView.Adapter<ServiceImageAdapter.MyViewHolder> {

    private Context context;
    private List<DataDetailsItem> dataDetailsItemList;
    private ClickListener clickListener;

    public ServiceImageAdapter(Context context, List<DataDetailsItem> dataDetailsItemList) {
        this.context = context;
        this.dataDetailsItemList = dataDetailsItemList;
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemBusinessAdvisorBinding binding = ItemBusinessAdvisorBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new ServiceImageAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        DataDetailsItem item = dataDetailsItemList.get(position);
        Glide.with(context)
                .load(item.getCompanyServiceMoreDetailsImage())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.binding.acivService);

        holder.binding.mtvTitle.setText(item.getCompanyServiceMoreDetailsName());

        holder.binding.acivService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null)
                    clickListener.onSelect(item,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataDetailsItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemBusinessAdvisorBinding binding;

        public MyViewHolder(@NonNull ItemBusinessAdvisorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
