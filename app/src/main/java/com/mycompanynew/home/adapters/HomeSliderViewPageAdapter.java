package com.mycompanynew.home.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.google.android.material.card.MaterialCardView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemHomeSlidePageBinding;

import java.util.Objects;

public class HomeSliderViewPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private int[] imageList = new int[]{R.drawable.banner_people};

    public HomeSliderViewPageAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (MaterialCardView)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ItemHomeSlidePageBinding binding = ItemHomeSlidePageBinding.inflate(LayoutInflater.from(container.getContext()),container, false);
//        View itemView = mLayoutInflater.inflate(R.layout.item_home_slide_page, container, false);

//        AppCompatImageView imageView = itemView.findViewById(R.id.aciv_slider);
//        imageView.setBackgroundResource(imageList[0]);

        Objects.requireNonNull(container).addView(binding.getRoot());
        return binding.getRoot();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        container.removeView((CardView) object);
    }
}
