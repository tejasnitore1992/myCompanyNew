package com.mycompanynew.our_services.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ActivityMainBinding;
import com.mycompanynew.databinding.ActivityServicesDetailsBinding;
import com.mycompanynew.our_services.adapters.ServiceImageAdapter;
import com.mycompanynew.our_services.adapters.ServiceImpPointAdapter;

public class ServicesDetailsActivity extends AppCompatActivity {

    private ActivityServicesDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityServicesDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvServiceImage.setAdapter(new ServiceImageAdapter());

        binding.rvPoint.setAdapter(new ServiceImpPointAdapter());

    }
}