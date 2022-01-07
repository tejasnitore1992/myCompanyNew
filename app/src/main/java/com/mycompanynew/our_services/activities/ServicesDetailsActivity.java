package com.mycompanynew.our_services.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ActivityMainBinding;
import com.mycompanynew.databinding.ActivityServicesDetailsBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.our_services.adapters.ServiceImageAdapter;
import com.mycompanynew.our_services.adapters.ServiceImpPointAdapter;

public class ServicesDetailsActivity extends AppCompatActivity {

    private ActivityServicesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServicesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ServiceImageAdapter serviceImageAdapter = new ServiceImageAdapter();
        serviceImageAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                startActivity(new Intent(ServicesDetailsActivity.this,ProductDetailsActivity.class));
            }
        });
        binding.rvServiceImage.setAdapter(serviceImageAdapter);

        binding.rvPoint.setAdapter(new ServiceImpPointAdapter());

    }
}