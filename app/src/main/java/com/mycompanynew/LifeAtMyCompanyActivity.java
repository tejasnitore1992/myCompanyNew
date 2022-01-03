package com.mycompanynew;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompanynew.adapters.CareerAdapter;

public class LifeAtMyCompanyActivity extends AppCompatActivity {


    private CareerAdapter careerAdapter;
    private RecyclerView rvCareer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_at_my_company);
        initView();

        rvCareer.setLayoutManager(new LinearLayoutManager(LifeAtMyCompanyActivity.this));
        careerAdapter = new CareerAdapter(LifeAtMyCompanyActivity.this, null);
        rvCareer.setAdapter(careerAdapter);

    }

    private void initView() {
        rvCareer = (RecyclerView) findViewById(R.id.rv_career);
    }
}