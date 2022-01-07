package com.mycompanynew;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.databinding.ItemCareerBinding;

public class HolidayAdapterDemo extends RecyclerView.Adapter<HolidayAdapterDemo.ViewHolder> {

    Context context;

    public HolidayAdapterDemo(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCareerBinding binding = ItemCareerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // binding.rootLayout
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemCareerBinding binding;
        public ViewHolder(@NonNull ItemCareerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
