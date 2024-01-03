package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;

import android.widget.Button;


public class PresentAdvisorFragment extends Fragment {

    private SharedViewModel sharedViewModel;


    public static PresentAdvisorFragment newInstance() {
        return new PresentAdvisorFragment();
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_present_advisor, container, false);
        //setSharedViewModel(new ViewModelProvider(requireActivity()).get(SharedViewModel.class));

        Button start_btn= rootView.findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                sharedViewModel.setRadioGroup1CheckedId(0);
                sharedViewModel.setRadioGroup2CheckedId(0);
                sharedViewModel.setRadioGroup3CheckedId(0);
                sharedViewModel.setRadioGroup4CheckedId(0);
                sharedViewModel.setRadioGroup5CheckedId(0);
                sharedViewModel.setRadioGroup6CheckedId(0);
                sharedViewModel.setRadioGroup7CheckedId(0);
                sharedViewModel.setRadioGroup8CheckedId(0);
                sharedViewModel.setRadioGroup9CheckedId(0);
                sharedViewModel.setRadioGroup10CheckedId(0);
                sharedViewModel.setRadioGroup11CheckedId(0);
                sharedViewModel.setRadioGroup12CheckedId(0);

                ((MainActivity)requireActivity()).replaceFragment(Tap3_Page1Fragment.newInstance());

            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
    public void setSharedViewModel(SharedViewModel viewModel) {
        this.sharedViewModel = viewModel;
    }
}