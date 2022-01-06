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
import com.mycompanynew.our_services.activities.ServicesDetailsActivity;
import com.mycompanynew.our_services.adapters.OurServicesAdapter;


public class OurServicesFragment extends Fragment {

    private FragmentOurServicesBinding binding;

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
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        OurServicesAdapter ourServicesAdapter = new OurServicesAdapter();
        ourServicesAdapter.setClickListener(new ClickListener() {
            @Override
            public void onSelect(Object obj, View view, int position) {
                startActivity(new Intent(getActivity(), ServicesDetailsActivity.class));
            }
        });
        binding.rvOurService.setAdapter(ourServicesAdapter);
    }
}