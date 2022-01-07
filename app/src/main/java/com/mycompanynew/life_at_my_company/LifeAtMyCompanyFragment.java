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

import com.mycompanynew.adapters.CareerAdapter;
import com.mycompanynew.databinding.FragmentLifeAtMyCompanyBinding;
import com.mycompanynew.interfaces.ClickListener;
import com.mycompanynew.life_at_my_company.activities.ApplyJobActivity;


public class LifeAtMyCompanyFragment extends Fragment {

    private FragmentLifeAtMyCompanyBinding binding;

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


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.rvCareer.setLayoutManager(new LinearLayoutManager(getActivity()));

        CareerAdapter careerAdapter = new CareerAdapter(getActivity(), null);
        careerAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                startActivity(new Intent(getActivity(), ApplyJobActivity.class));
            }
        });
        binding.rvCareer.setAdapter(careerAdapter);
    }
}