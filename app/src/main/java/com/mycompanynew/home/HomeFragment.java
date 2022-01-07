package com.mycompanynew.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.mycompanynew.databinding.FragmentHomeBinding;
import com.mycompanynew.home.adapters.CurrentOpeningAdapter;
import com.mycompanynew.home.adapters.HomeSliderViewPageAdapter;
import com.mycompanynew.home.adapters.OurServiceAdapter;
import com.mycompanynew.home.adapters.OurServiceTextAdapter;
import com.mycompanynew.home.response.AboutCompany;
import com.mycompanynew.home.response.CurrentOpeningItem;
import com.mycompanynew.home.response.DashboardResponse;
import com.mycompanynew.home.response.OurServicesItem;
import com.mycompanynew.home.response.ServiceListItem;
import com.mycompanynew.home.response.SliderItem;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.life_at_my_company.activities.ApplyJobActivity;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private List<SliderItem> slider = new ArrayList<>();
    private List<OurServicesItem> ourServices = new ArrayList<>();
    private List<ServiceListItem> servicePointList = new ArrayList<>();
    private List<CurrentOpeningItem> currentOpening = new ArrayList<>();

    private HomeSliderViewPageAdapter homeSliderViewPageAdapter;
    private OurServiceAdapter ourServiceAdapter;
    private OurServiceTextAdapter ourServiceTextAdapter;
    private CurrentOpeningAdapter currentOpeningAdapter;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tools = new Tools(requireActivity());
        preferenceManager = new PreferenceManager(requireActivity());

        restCall = RestClient.createService(RestCall.class, preferenceManager.getBaseUrl(),
                preferenceManager.getApiKey(),
                preferenceManager.getUserName(),
                Tools.getCurrentPassword(preferenceManager.getSocietyId(),
                        preferenceManager.getRegisteredUserId(),
                        preferenceManager.getKeyValueString(VariableBag.USER_Mobile)));

        setHomeSlider();
        setOurService();
        setCurrentOpening();
        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefreshLayout.setRefreshing(false);
                getData();
            }
        });
        getData();
    }

    private void setCurrentOpening() {

        binding.mbtnCurrentOpeningViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), ApplyJobActivity.class));
            }
        });

        currentOpeningAdapter = new CurrentOpeningAdapter(getActivity(), currentOpening);
        currentOpeningAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                startActivity(new Intent(getActivity(), ApplyJobActivity.class));
            }
        });
        binding.vpCurrentOpening.setAdapter(currentOpeningAdapter);
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
        ourServiceAdapter = new OurServiceAdapter(getActivity(), ourServices, binding.vpOurService);
        binding.vpOurService.setAdapter(ourServiceAdapter);
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
                OurServicesItem ourServicesItem = ourServices.get(position);
                servicePointList.clear();
                if (ourServicesItem.getServiceList() != null)
                    servicePointList.addAll(ourServicesItem.getServiceList());
                if (ourServiceTextAdapter != null)
                    ourServiceTextAdapter.notifyDataSetChanged();
            }
        });

        ourServiceTextAdapter = new OurServiceTextAdapter(getActivity(), servicePointList);
        binding.rvOurService.setAdapter(ourServiceTextAdapter);

    }

    private void setHomeSlider() {
        homeSliderViewPageAdapter = new HomeSliderViewPageAdapter(getActivity(), slider);
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

    public void getData() {
        restCall.getDashboardData(
                "getDashboardData",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<DashboardResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nestedScrollView.setVisibility(View.GONE);
                                binding.llMsg.setVisibility(View.VISIBLE);
                                binding.mtvMsg.setText("Something went wrong!!!");
                            }
                        });
                    }

                    @Override
                    public void onNext(DashboardResponse response) {

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (response.getStatus().equalsIgnoreCase(VariableBag.SUCCESS_CODE)) {
                                    // code here
                                    binding.nestedScrollView.setVisibility(View.VISIBLE);
                                    binding.llMsg.setVisibility(View.GONE);
                                    setValues(response);

                                } else {
                                    // No data view
                                    binding.nestedScrollView.setVisibility(View.GONE);
                                    binding.llMsg.setVisibility(View.VISIBLE);
                                    binding.mtvMsg.setText("Something went wrong!!!");
                                }
                            }
                        });
                    }
                });
    }

    private void setValues(DashboardResponse response) {

        slider.clear();
        slider.addAll(response.getSlider());
        if (homeSliderViewPageAdapter != null)
            homeSliderViewPageAdapter.notifyDataSetChanged();

        AboutCompany aboutCompany = response.getAboutCompany();
        binding.mtvCompanyName.setText(aboutCompany.getCompanyHomeTitle());
        binding.mtvCompanyDescription.setText(aboutCompany.getCompanyHomeDescription());

        ourServices.clear();
        ourServices.addAll(response.getOurServices());
        if (ourServiceAdapter != null)
            ourServiceAdapter.notifyDataSetChanged();

        if (!ourServices.isEmpty()) {
            OurServicesItem ourServicesItem = ourServices.get(0);
            servicePointList.clear();
            if (ourServicesItem.getServiceList() != null)
                servicePointList.addAll(ourServicesItem.getServiceList());
            if (ourServiceTextAdapter != null)
                ourServiceTextAdapter.notifyDataSetChanged();
        }

        if (!ourServices.isEmpty()) {
            binding.llcOurServices.setVisibility(View.VISIBLE);
        } else {
            binding.llcOurServices.setVisibility(View.GONE);
        }

        currentOpening.clear();
        currentOpening.addAll(response.getCurrentOpening());
        if (currentOpeningAdapter != null)
            currentOpeningAdapter.notifyDataSetChanged();

        if (!currentOpening.isEmpty()) {
            binding.llcCurrentOpening.setVisibility(View.VISIBLE);
        } else {
            binding.llcCurrentOpening.setVisibility(View.GONE);
        }
    }
}