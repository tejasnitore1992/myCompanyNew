package com.mycompanynew.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemCareerBinding;
import com.mycompanynew.interfaces.ClickListener;

import java.util.List;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.MyViewHolder> {

    private Context context;
    public List<Object> objectList;

    private ClickListener clickListener;

    public CareerAdapter(Context context, List<Object> objectList) {
        this.context = context;
        this.objectList = objectList;
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
        return 3;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ItemCareerBinding binding;


        public MyViewHolder(ItemCareerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
