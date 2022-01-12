package com.mycompanynew.life_at_my_company;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompanynew.about_us.response.AboutUsResponse;
import com.mycompanynew.adapters.CareerAdapter;
import com.mycompanynew.databinding.FragmentLifeAtMyCompanyBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.life_at_my_company.activities.ApplyJobActivity;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningItem;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningResponse;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.utils.IntentData;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;


public class LifeAtMyCompanyFragment extends Fragment {

    private FragmentLifeAtMyCompanyBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    private List<CurrentOpeningItem> itemList = new ArrayList<>();
    private CareerAdapter careerAdapter;

    public LifeAtMyCompanyFragment() {
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
        binding = FragmentLifeAtMyCompanyBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        tools = new Tools(requireActivity());
        preferenceManager = new PreferenceManager(requireActivity());

        restCall = RestClient.createService(RestCall.class, preferenceManager.getBaseUrl(),
                preferenceManager.getApiKey(),
                preferenceManager.getUserName(),
                Tools.getCurrentPassword(preferenceManager.getSocietyId(),
                        preferenceManager.getRegisteredUserId(),
                        preferenceManager.getKeyValueString(VariableBag.USER_Mobile)));

        binding.loader.rlLoader.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvCareer.setLayoutManager(new LinearLayoutManager(getActivity()));

        careerAdapter = new CareerAdapter(getActivity(), itemList);
        careerAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {

                CurrentOpeningItem openingItem = (CurrentOpeningItem) obj;
                Intent intent = new Intent(getActivity(), ApplyJobActivity.class);
                intent.putExtra(IntentData.INTENT_CURRENT_OPENING_ITEM,openingItem);
                startActivity(intent);
            }
        });
        binding.rvCareer.setAdapter(careerAdapter);
        getData();
    }

    public void getData() {
        binding.loader.rlLoader.setVisibility(View.VISIBLE);
        restCall.getCurrentOpeningData(
                "getCurrentOpening",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<CurrentOpeningResponse>() {
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
                    public void onNext(CurrentOpeningResponse response) {

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

    private void setData(CurrentOpeningResponse response) {
        itemList.clear();
        itemList.addAll(response.getOpeningItemList());
        if(careerAdapter != null)
            careerAdapter.notifyDataSetChanged();
    }
}