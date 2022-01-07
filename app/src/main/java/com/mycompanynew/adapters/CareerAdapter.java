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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_career,parent,false);
        return new CareerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.mbtnApply.setOnClickListener(new View.OnClickListener() {
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

        MaterialTextView mtvFullTime;
        MaterialButton mbtnApply;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mtvFullTime = (MaterialTextView) itemView.findViewById(R.id.mtv_full_time);
            mbtnApply = (MaterialButton) itemView.findViewById(R.id.mbtn_apply);
        }
    }
}
