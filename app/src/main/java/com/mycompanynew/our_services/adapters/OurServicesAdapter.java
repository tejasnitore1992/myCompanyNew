package com.mycompanynew.our_services.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemServicesBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.our_services.response.OurServicesItem;

import java.util.List;

public class OurServicesAdapter extends RecyclerView.Adapter<OurServicesAdapter.MyViewHolder> {

    private Context context;
    public List<OurServicesItem> servicesItemList;

    private ClickListener clickListener;

    public OurServicesAdapter(Context context, List<OurServicesItem> servicesItemList) {
        this.context = context;
        this.servicesItemList = servicesItemList;
    }

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

        OurServicesItem ourServicesItem = servicesItemList.get(position);

        Glide.with(context)
                .load(ourServicesItem.getCompanyServiceImage())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(holder.binding.acivServiceImage);

        holder.binding.mtvServiceName.setText(ourServicesItem.getCompanyServiceName());

        holder.binding.llcService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clickListener != null)
                    clickListener.onSelect(ourServicesItem,view,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesItemList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ItemServicesBinding binding;
        public MyViewHolder(@NonNull ItemServicesBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
