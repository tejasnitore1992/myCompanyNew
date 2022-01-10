package com.mycompanynew.our_services.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompanynew.databinding.ActivityServicesDetailsBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.network.RestCall;
import com.mycompanynew.network.RestClient;
import com.mycompanynew.our_services.adapters.ServiceImageAdapter;
import com.mycompanynew.our_services.adapters.ServiceImpPointAdapter;
import com.mycompanynew.our_services.response.DataDetailsItem;
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

public class ServicesDetailsActivity extends AppCompatActivity {

    private ActivityServicesDetailsBinding binding;

    private RestCall restCall;
    private Tools tools;
    private PreferenceManager preferenceManager;

    private ServiceImageAdapter serviceImageAdapter;
    private ServiceImpPointAdapter serviceImpPointAdapter;

    private List<DataDetailsItem> dataDetailsItemList = new ArrayList<>();
    private List<ServiceDetailsItem> serviceDetailsItemList = new ArrayList<>();

    private String companyServiceId = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServicesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        tools = new Tools(ServicesDetailsActivity.this);
        preferenceManager = new PreferenceManager(ServicesDetailsActivity.this);

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
        if(bundle != null && bundle.containsKey(IntentKey.COMPANY_SERVICE_ID)
         && bundle.containsKey(IntentKey.COMPANY_SERVICE_NAME)){
            companyServiceId = bundle.getString(IntentKey.COMPANY_SERVICE_ID,"1");
            binding.materialToolbar.setTitle(bundle.getString(IntentKey.COMPANY_SERVICE_NAME,""));
        }else{
            Toast.makeText(ServicesDetailsActivity.this,"Missing company service id",Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }

    private void init(){
        getIntentData();
        serviceImageAdapter = new ServiceImageAdapter(ServicesDetailsActivity.this,dataDetailsItemList);
        serviceImageAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                DataDetailsItem dataDetailsItem = (DataDetailsItem) obj;
                Intent intent = new Intent(ServicesDetailsActivity.this,ProductDetailsActivity.class);
                intent.putExtra(IntentKey.COMPANY_SERVICE_DETAILS_ID,dataDetailsItem.getCompanyServiceMoreDetailsId());
                startActivity(intent);
            }
        });
        binding.rvServiceImage.setAdapter(serviceImageAdapter);

        serviceImpPointAdapter = new ServiceImpPointAdapter(ServicesDetailsActivity.this,serviceDetailsItemList);

        binding.rvPoint.setAdapter(serviceImpPointAdapter);
        binding.materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        getData();
    }

    public void getData() {

        restCall.getOurServiceDetailData(
                "getOurServicesDetails",
                preferenceManager.getSocietyId(),
                preferenceManager.getLanguageId(),
                companyServiceId
                )
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .subscribe(new Subscriber<ServicesDetailsResponse>() {
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
                    public void onNext(ServicesDetailsResponse response) {

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

    private void setData(ServicesDetailsResponse response) {


        binding.mtvTopTitle.setText(response.getCompanyServiceDetailsTitle());
        binding.mtvTopDescription.setText(response.getCompanyServiceDetailsDescription());
        dataDetailsItemList.clear();
        dataDetailsItemList.addAll(response.getDataDetailsItemList());
        if(serviceImageAdapter != null)
            serviceImageAdapter.notifyDataSetChanged();

        binding.mtvBottomTitle.setText("");
        binding.mtvBottomDescription.setText("");

        binding.mtvBottomTitle.setVisibility(View.GONE);
        binding.mtvBottomDescription.setVisibility(View.GONE);

        serviceDetailsItemList.clear();
        serviceDetailsItemList.addAll(response.getServiceDetailsItemList());
        if(serviceImpPointAdapter != null)
            serviceImpPointAdapter.notifyDataSetChanged();
    }

}