package com.mycompanynew.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.makeramen.roundedimageview.RoundedImageView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemOurServiceBinding;

public class OurServiceAdapter extends RecyclerView.Adapter<OurServiceAdapter.MyViewHolder> {

    private int[] imageList;
    private ViewPager2 viewPager2;

    public OurServiceAdapter(int[] imageList, ViewPager2 viewPager2) {
        this.imageList = imageList;
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

        holder.binding.roundedImageView.setImageResource(imageList[position]);
        if (position == imageList.length - 2){
//            viewPager2.post(runnable);
        }
    }

    @Override
    public int getItemCount() {
        return imageList.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ItemOurServiceBinding binding;

        public MyViewHolder(@NonNull ItemOurServiceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
}
