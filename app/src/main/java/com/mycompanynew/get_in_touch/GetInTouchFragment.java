package com.mycompanynew.get_in_touch;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycompanynew.R;
import com.mycompanynew.databinding.FragmentGetInTouchBinding;
import com.mycompanynew.databinding.FragmentOurServicesBinding;


public class GetInTouchFragment extends Fragment {

    private FragmentGetInTouchBinding binding;

    public GetInTouchFragment() {
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
        binding = FragmentGetInTouchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.loader.rlLoader.setVisibility(View.GONE);

    }
}