package com.mycompanynew.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceTextBinding;

import java.util.List;

public class OurServiceTextAdapter extends RecyclerView.Adapter<OurServiceTextAdapter.MyViewHolder> {


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ItemOurServiceTextBinding binding = ItemOurServiceTextBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_service_text,parent,false);

        return new OurServiceTextAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceTextBinding binding;
        public MyViewHolder(@NonNull ItemOurServiceTextBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
