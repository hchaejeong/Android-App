package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.widget.Button;


public class PresentAdvisorFragment extends Fragment {

    public static PresentAdvisorFragment newInstance() {
        return new PresentAdvisorFragment();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_present_advisor, container, false);

        Button start_btn= rootView.findViewById(R.id.start_btn);
        start_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(Tap3_Page1Fragment.newInstance());
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}