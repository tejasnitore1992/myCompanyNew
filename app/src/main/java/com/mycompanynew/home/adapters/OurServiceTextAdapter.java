package com.mycompanynew.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceTextBinding;
import com.mycompanynew.home.response.ServiceListItem;

import java.util.List;

public class OurServiceTextAdapter extends RecyclerView.Adapter<OurServiceTextAdapter.MyViewHolder> {

    private Context context;
    private List<ServiceListItem> serviceList;

    public OurServiceTextAdapter(Context context, List<ServiceListItem> serviceList) {
        this.context = context;
        this.serviceList = serviceList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOurServiceTextBinding binding = ItemOurServiceTextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_service_text,parent,false);

        return new OurServiceTextAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ServiceListItem item = serviceList.get(position);
        holder.binding.mtvServicePoint.setText(item.getCompanyServiceDetailListName());
    }

    @Override
    public int getItemCount() {
        return serviceList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceTextBinding binding;
        public MyViewHolder(@NonNull ItemOurServiceTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
