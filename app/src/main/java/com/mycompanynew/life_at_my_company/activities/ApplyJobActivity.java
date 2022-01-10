package com.mycompanynew.life_at_my_company.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mycompanynew.R;
import com.mycompanynew.databinding.ActivityApplyJobBinding;
import com.mycompanynew.life_at_my_company.response.CurrentOpeningItem;

import java.util.ArrayList;
import java.util.List;

public class ApplyJobActivity extends AppCompatActivity {

    private Context context;
    private ActivityApplyJobBinding binding;

    private List<CurrentOpeningItem> currentOpeningItemList = new ArrayList<>();
    private ArrayAdapter<CurrentOpeningItem> spinnerPositionArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityApplyJobBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = ApplyJobActivity.this;

        init();
    }

    private void init(){

        spinnerPositionArrayAdapter = new ArrayAdapter<CurrentOpeningItem>(this, android.R.layout.simple_spinner_item,currentOpeningItemList);
        spinnerPositionArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        binding.acsPosition.setAdapter(spinnerPositionArrayAdapter);


        binding.mbtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fieldValidation();
            }
        });

    }

    private void fieldValidation() {

        CurrentOpeningItem selectedOpening = (CurrentOpeningItem) binding.acsPosition.getSelectedItem();
        String name = binding.tietName.getText().toString();
        String dateOfBirth = binding.tietDateOfBirth.getText().toString();
        String gender = null;
        if(binding.rbMale.isChecked()){
            gender = binding.rbMale.getText().toString();
        }else if(binding.rbFemale.isChecked()){
            gender = binding.rbFemale.getText().toString();
        }else if(binding.rbGenderOther.isChecked()){
            gender = binding.rbGenderOther.getText().toString();
        }
        String state = binding.tietState.getText().toString();
        String city = binding.tietCity.getText().toString();
        String contactNo = binding.tietContactNo.getText().toString();
        String emailId = binding.tietEmailId.getText().toString();
        String education = null;
        if(binding.rbGraduate.isChecked()){
            education = binding.rbGraduate.getText().toString();
        }else if(binding.rbPostGraduate.isChecked()){
            education = binding.rbPostGraduate.getText().toString();
        }else if(binding.rbEducationOther.isChecked()){
            education = binding.rbEducationOther.getText().toString();
        }

        String totalYearOfExperience = (String) binding.acsTotalYearOfExperience.getSelectedItem();

        if(Integer.parseInt(selectedOpening.getCompanyCurrentOpeningId()) < 1){
            Toast.makeText(context,"Please select position!!!",Toast.LENGTH_LONG).show();
        }
    }
}