package com.mycompanynew.our_services.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.R;
import com.mycompanynew.interfaces.ClickListener;

public class ServiceImageAdapter extends RecyclerView.Adapter<ServiceImageAdapter.MyViewHolder> {

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_business_advisor,parent,false);
        return new ServiceImageAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.acivService.setOnClickListener(new View.OnClickListener() {
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
        AppCompatImageView acivService;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            acivService = (AppCompatImageView) itemView.findViewById(R.id.aciv_service);
        }
    }
}
