package com.mycompanynew.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceBinding;
import com.mycompanynew.home.response.OurServicesItem;

import java.util.ArrayList;
import java.util.List;

public class OurServiceAdapter extends RecyclerView.Adapter<OurServiceAdapter.MyViewHolder> {

    private Context context;
    private List<OurServicesItem> ourServices;
    private ViewPager2 viewPager2;

    public OurServiceAdapter(Context context, List<OurServicesItem> ourServices, ViewPager2 viewPager2) {
        this.context = context;
        this.ourServices = ourServices;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOurServiceBinding binding = ItemOurServiceBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_our_service,parent,false);
        return new OurServiceAdapter.MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        OurServicesItem ourServicesItem = ourServices.get(position);

        Glide.with(context)
                .load(ourServicesItem.getCompanyServiceImage())
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_broken_image)
                .into(holder.binding.roundedImageView);

        holder.binding.mtvTitle.setText(ourServicesItem.getCompanyServiceName());
//        holder.binding.roundedImageView.setImageResource(imageList[position]);
//        if (position == imageList.length - 2){
//            viewPager2.post(runnable);
//        }
    }

    @Override
    public int getItemCount() {
        return ourServices.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceBinding binding;

        public MyViewHolder(@NonNull ItemOurServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
