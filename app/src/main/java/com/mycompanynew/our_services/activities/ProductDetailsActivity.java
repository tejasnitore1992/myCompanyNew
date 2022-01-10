package com.mycompanynew.our_services.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mycompanynew.R;
import com.mycompanynew.databinding.ActivityProductDetailsBinding;
import com.mycompanynew.databinding.ActivityServicesDetailsBinding;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.our_services.adapters.ServiceImageAdapter;
import com.mycompanynew.our_services.adapters.ServiceImpPointAdapter;
import com.mycompanynew.our_services.adapters.ServiceMoreImpPointAdapter;
import com.mycompanynew.our_services.response.DataServiceDetailsMoreItem;
import com.mycompanynew.our_services.response.ServiceDetailMoreResponse;
import com.mycompanynew.our_services.response.ServiceDetailsItem;
import com.mycompanynew.our_services.response.ServicesDetailsResponse;
import com.mycompanynew.utils.IntentKey;
import com.mycompanynew.utils.PreferenceManager;
import com.mycompanynew.utils.Tools;
import com.mycompanynew.utils.VariableBag;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.schedulers.Schedulers;

public class ProductDetailsActivity extends AppCompatActivity {

    private ActivityProductDetailsBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    private ServiceMoreImpPointAdapter serviceMoreImpPointAdapter;
    private List<DataServiceDetailsMoreItem> serviceDetailsItemList = new ArrayList<>();

    private String companyServiceDetailsId = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tools = new Tools(ProductDetailsActivity.this);
        preferenceManager = new PreferenceManager(ProductDetailsActivity.this);

        restCall = RestClient.createService(RestCall.class, preferenceManager.getBaseUrl(),
                preferenceManager.getApiKey(),
                preferenceManager.getUserName(),
                Tools.getCurrentPassword(preferenceManager.getSocietyId(),
                        preferenceManager.getRegisteredUserId(),
                        preferenceManager.getKeyValueString(VariableBag.USER_Mobile)));

        init();

    }

    private void getIntentData() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(IntentKey.COMPANY_SERVICE_DETAILS_ID)){
            companyServiceDetailsId = bundle.getString(IntentKey.COMPANY_SERVICE_DETAILS_ID,"1");
        }else{
            Toast.makeText(ProductDetailsActivity.this,"Missing company service details id",Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    private void init(){
        getIntentData();
        serviceMoreImpPointAdapter = new ServiceMoreImpPointAdapter(ProductDetailsActivity.this,serviceDetailsItemList);
        binding.rvPoint.setAdapter(serviceMoreImpPointAdapter);
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getData();
    }

    public void getData() {

        restCall.getOurServiceDetailMoreData(
                "getOurServicesDetailsMore",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId(),
                companyServiceDetailsId
        )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ServiceDetailMoreResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                binding.nestedScrollView.setVisibility(View.GONE);
                                binding.llMsg.setVisibility(View.VISIBLE);
                                binding.mtvMsg.setText("Something went wrong!!!");
                            }
                        });
                    }

                    @Override
                    public void onNext(ServiceDetailMoreResponse response) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
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

    private void setData(ServiceDetailMoreResponse response) {

        Glide.with(ProductDetailsActivity.this)
                .load(response.getCompanyServiceMoreDetailsImage())
                .placeholder(R.drawable.ic_broken_image)
                .error(R.drawable.ic_broken_image)
                .into(binding.acivImage);

        binding.materialToolbar.setTitle(response.getCompanyServiceMoreDetailsName());
        binding.mtvTitle.setText(response.getCompanyServiceMoreDetailsTitle());
        binding.mtvDescription.setText(response.getCompanyServiceMoreDetailsDescription());

        serviceDetailsItemList.clear();
        serviceDetailsItemList.addAll(response.getDataDetailsItemList());
        if(serviceMoreImpPointAdapter != null)
            serviceMoreImpPointAdapter.notifyDataSetChanged();

    }

}