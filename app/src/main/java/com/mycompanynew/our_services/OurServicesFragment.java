package com.mycompanynew.our_services;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompanynew.R;
import com.mycompanynew.databinding.FragmentHomeBinding;
import com.mycompanynew.databinding.FragmentOurServicesBinding;


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
}