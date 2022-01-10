package com.mycompanynew.our_services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.databinding.ItemOurServiceTextBinding;
import com.mycompanynew.our_services.response.DataServiceDetailsMoreItem;

import java.util.List;

public class ServiceMoreImpPointAdapter extends RecyclerView.Adapter<ServiceMoreImpPointAdapter.MyViewHolder> {

    private Context context;
    private List<DataServiceDetailsMoreItem> detailsMoreItemList;

    public ServiceMoreImpPointAdapter(Context context, List<DataServiceDetailsMoreItem> detailsMoreItemList) {
        this.context = context;
        this.detailsMoreItemList = detailsMoreItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOurServiceTextBinding binding = ItemOurServiceTextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ServiceMoreImpPointAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataServiceDetailsMoreItem moreItem = detailsMoreItemList.get(position);
        holder.binding.mtvServicePoint.setText(moreItem.getCompanyServiceMoreDetailListName());
    }

    @Override
    public int getItemCount() {
        return detailsMoreItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceTextBinding binding;
        public MyViewHolder(@NonNull ItemOurServiceTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
