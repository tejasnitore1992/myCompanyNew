package com.mycompanynew.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.mycompanynew.R;
import com.mycompanynew.databinding.FragmentHomeBinding;
import com.mycompanynew.home.adapters.CurrentOpeningAdapter;
import com.mycompanynew.home.adapters.HomeSliderViewPageAdapter;
import com.mycompanynew.home.adapters.OurServiceAdapter;
import com.mycompanynew.home.adapters.OurServiceTextAdapter;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeSliderViewPageAdapter homeSliderViewPageAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHomeSlider();
        setOurService();
        setCurrentOpening();
    }

    private void setCurrentOpening() {

        binding.vpCurrentOpening.setAdapter(new CurrentOpeningAdapter());
        binding.vpCurrentOpening.setClipToPadding(false);
        binding.vpCurrentOpening.setClipChildren(false);
        binding.vpCurrentOpening.setOffscreenPageLimit(3);
        binding.vpCurrentOpening.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

//        SnapHelper snapHelper = new PagerSnapHelper();
//        snapHelper.attachToRecyclerView(binding.rvCareer);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });


        binding.vpCurrentOpening.setPageTransformer(compositePageTransformer);

        binding.vpCurrentOpening.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable);
//                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });
    }

    private void setOurService() {

        int[] imageList = new int[]{R.drawable.our_service_banner,
                R.drawable.our_service_banner,
                R.drawable.our_service_banner,
                R.drawable.our_service_banner};
        binding.vpOurService.setAdapter(new OurServiceAdapter(imageList,binding.vpOurService));
        binding.vpOurService.setClipToPadding(false);
        binding.vpOurService.setClipChildren(false);
        binding.vpOurService.setOffscreenPageLimit(3);
        binding.vpOurService.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        binding.vpOurService.setPageTransformer(compositePageTransformer);

        binding.vpOurService.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
//                sliderHandler.removeCallbacks(sliderRunnable);
//                sliderHandler.postDelayed(sliderRunnable, 2000); // slide duration 2 seconds
            }
        });

        binding.rvOurService.setAdapter(new OurServiceTextAdapter());

    }

    private void setHomeSlider() {
        homeSliderViewPageAdapter = new HomeSliderViewPageAdapter(getActivity());
        binding.vpSlider.setAdapter(homeSliderViewPageAdapter);
        binding.vpSlider.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.indicator.attachToPager(binding.vpSlider);
    }
}