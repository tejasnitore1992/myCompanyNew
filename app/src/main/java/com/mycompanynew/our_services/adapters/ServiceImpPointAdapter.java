package com.mycompanynew.our_services.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceTextBinding;

public class ServiceImpPointAdapter extends RecyclerView.Adapter<ServiceImpPointAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOurServiceTextBinding binding = ItemOurServiceTextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_service_text,parent,false);
        return new ServiceImpPointAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceTextBinding binding;
        public MyViewHolder(@NonNull ItemOurServiceTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
