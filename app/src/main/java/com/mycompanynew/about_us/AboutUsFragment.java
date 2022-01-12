package com.mycompanynew.about_us;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.mycompanynew.R;
import com.mycompanynew.about_us.response.AboutUsResponse;
import com.mycompanynew.databinding.FragmentAboutUsBinding;
import com.mycompanynew.home.response.DashboardResponse;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import rx.Subscriber;
import rx.schedulers.Schedulers;


public class AboutUsFragment extends Fragment {

    private FragmentAboutUsBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    public AboutUsFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAboutUsBinding.inflate(inflater,container, false);
        View view = binding.getRoot();
        return view;
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

       /* binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                binding.swipeRefreshLayout.setRefreshing(false);
                getData();
            }
        });*/

        getData();
    }

    public void getData() {
        binding.loader.rlLoader.setVisibility(View.VISIBLE);
        restCall.getAboutUsData(
                "getAboutUs",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<AboutUsResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.loader.rlLoader.setVisibility(View.GONE);
                                binding.nestedScrollView.setVisibility(View.GONE);
                                binding.llMsg.setVisibility(View.VISIBLE);
                                binding.mtvMsg.setText("Something went wrong!!!");
                            }
                        });
                    }

                    @Override
                    public void onNext(AboutUsResponse response) {

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.loader.rlLoader.setVisibility(View.GONE);
                                if (response.getStatus().equalsIgnoreCase(VariableBag.SUCCESS_CODE)) {
                                    // code here
                                    binding.nestedScrollView.setVisibility(View.VISIBLE);
                                    binding.llMsg.setVisibility(View.GONE);

                                    setData(response);
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

    private void setData(AboutUsResponse response) {

        Glide.with(getActivity())
                .load(response.getCompanyAboutUsTopImage())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(binding.acivTopImage);

        binding.mtvTopDescription.setText(response.getCompanyAboutUsTopDescription());

        Glide.with(getActivity())
                .load(response.getCompanyAboutUsImageOne())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(binding.acivImageFirst);

        Glide.with(getActivity())
                .load(response.getCompanyAboutUsImageTwo())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(binding.acivImageSecond);

        binding.mtvBottomTitle.setText(response.getCompanyAboutUsBottomTitle());

        binding.mtvBottomDescription.setText(response.getCompanyAboutUsBottomDescription());
    }
}