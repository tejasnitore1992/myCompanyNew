package com.mycompanynew.our_services.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceTextBinding;
import com.mycompanynew.our_services.response.ServiceDetailsItem;

import java.util.List;

public class ServiceImpPointAdapter extends RecyclerView.Adapter<ServiceImpPointAdapter.MyViewHolder> {

    private Context context;
    public List<ServiceDetailsItem> serviceDetailsItemList;

    public ServiceImpPointAdapter(Context context, List<ServiceDetailsItem> serviceDetailsItemList) {
        this.context = context;
        this.serviceDetailsItemList = serviceDetailsItemList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOurServiceTextBinding binding = ItemOurServiceTextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ServiceImpPointAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServiceDetailsItem serviceDetailsItem = serviceDetailsItemList.get(position);

        holder.binding.mtvServicePoint.setText(serviceDetailsItem.getCompanyServiceDetailListName());
    }

    @Override
    public int getItemCount() {
        return serviceDetailsItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceTextBinding binding;
        public MyViewHolder(@NonNull ItemOurServiceTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
