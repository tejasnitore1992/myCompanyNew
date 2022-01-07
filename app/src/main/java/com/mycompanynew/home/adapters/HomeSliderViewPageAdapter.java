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

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ItemHomeSlidePageBinding;
import com.mycompanynew.home.response.SliderItem;

import java.util.List;
import java.util.Objects;

public class HomeSliderViewPageAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater mLayoutInflater;
    private List<SliderItem> slider;


    public HomeSliderViewPageAdapter(Context context,List<SliderItem> slider) {
        this.context = context;
        this.slider = slider;
        this.mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return slider.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (MaterialCardView)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        SliderItem sliderItem = slider.get(position);
        ItemHomeSlidePageBinding binding = ItemHomeSlidePageBinding.inflate(LayoutInflater.from(container.getContext()),container, false);
//        View itemView = mLayoutInflater.inflate(R.layout.item_home_slide_page, container, false);

//        AppCompatImageView imageView = itemView.findViewById(R.id.aciv_slider);
//        imageView.setBackgroundResource(imageList[0]);

        Glide.with(context)
                .load(sliderItem.getCompanyHomeSliderImage())
                .placeholder(R.drawable.ic_refresh)
                .error(R.drawable.ic_broken_image)
                .into(binding.acivSlider);

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
