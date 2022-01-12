package com.mycompanynew.our_services;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompanynew.databinding.FragmentOurServicesBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningResponse;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.our_services.activities.ServicesDetailsActivity;
import com.mycompanynew.our_services.adapters.OurServicesAdapter;
import com.mycompanynew.our_services.response.OurServicesItem;
import com.mycompanynew.our_services.response.OurServicesResponse;
import com.mycompanynew.utils.IntentKey;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;


public class OurServicesFragment extends Fragment {

    private FragmentOurServicesBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    private OurServicesAdapter ourServicesAdapter;
    private List<OurServicesItem> servicesItemList = new ArrayList<>();

    public OurServicesFragment() {
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
        binding = FragmentOurServicesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tools = new Tools(requireActivity());
        preferenceManager = new PreferenceManager(requireActivity());

        restCall = RestClient.createService(RestCall.class, preferenceManager.getBaseUrl(),
                preferenceManager.getApiKey(),
                preferenceManager.getUserName(),
                Tools.getCurrentPassword(preferenceManager.getSocietyId(),
                        preferenceManager.getRegisteredUserId(),
                        preferenceManager.getKeyValueString(VariableBag.USER_Mobile)));

        binding.loader.rlLoader.setVisibility(View.GONE);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ourServicesAdapter = new OurServicesAdapter(getActivity(),servicesItemList);
        ourServicesAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                OurServicesItem ourServicesItem = (OurServicesItem) obj;
                Intent intent = new Intent(getActivity(), ServicesDetailsActivity.class);
                intent.putExtra(IntentKey.COMPANY_SERVICE_ID,ourServicesItem.getCompanyServiceId());
                intent.putExtra(IntentKey.COMPANY_SERVICE_NAME,ourServicesItem.getCompanyServiceName());
                startActivity(intent);
            }
        });
        binding.rvOurService.setAdapter(ourServicesAdapter);
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        getData();
    }

    public void getData() {
        binding.loader.rlLoader.setVisibility(View.VISIBLE);
        restCall.getOurServiceData(
                "getOurServices",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId())
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<OurServicesResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.loader.rlLoader.setVisibility(View.GONE);
                                binding.rvOurService.setVisibility(View.GONE);
                                binding.llMsg.setVisibility(View.VISIBLE);
                                binding.mtvMsg.setText("Something went wrong!!!");
                            }
                        });
                    }

                    @Override
                    public void onNext(OurServicesResponse response) {

                        requireActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.loader.rlLoader.setVisibility(View.GONE);
                                if (response.getStatus().equalsIgnoreCase(VariableBag.SUCCESS_CODE)) {
                                    // code here
                                    binding.rvOurService.setVisibility(View.VISIBLE);
                                    binding.llMsg.setVisibility(View.GONE);

                                    setData(response);
                                } else {
                                    // No data view
                                    binding.rvOurService.setVisibility(View.GONE);
                                    binding.llMsg.setVisibility(View.VISIBLE);
                                    binding.mtvMsg.setText("Something went wrong!!!");
                                }
                            }
                        });
                    }
                });
    }

    private void setData(OurServicesResponse response) {
        servicesItemList.clear();
        servicesItemList.addAll(response.getServicesItemList());
        if(ourServicesAdapter != null)
            ourServicesAdapter.notifyDataSetChanged();
    }
}