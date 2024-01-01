package com.example.myapplication.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;
import com.example.myapplication.Tap3_Page1;
import com.example.myapplication.Tap3_Page2;

import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.Fragment;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Tap3_Page2Fragment extends Fragment {
    private SharedViewModel sharedViewModel;
    private View view;
    private int e_count=0;
    private int i_count=0;

    public static Tap3_Page2Fragment newInstance() {
        return new Tap3_Page2Fragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tap3_page2, container, false);

        RadioGroup radioGroup1 = view.findViewById(R.id.radioGroup1);
        // 두 번째 질문과 라디오 그룹
        RadioGroup radioGroup2 = view.findViewById(R.id.radioGroup2);
        // 세 번째 질문과 라디오 그룹
        RadioGroup radioGroup3 = view.findViewById(R.id.radioGroup3);

        Button before1 = view.findViewById(R.id.before1);
        before1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).replaceFragment(Tap3_Page1Fragment.newInstance());
            }
        });

        Button after1 = view.findViewById(R.id.after1);
        after1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areAllRadioGroupsChecked()) {
                    ((MainActivity)getActivity()).replaceFragment(Tap3_Page3Fragment.newInstance());
                }
                else {
                    Toast.makeText(getActivity(),"선택되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 첫 번째 라디오 버튼
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_1) {
                    sharedViewModel.setData_1(0);
                }
                else if (checkedId == R.id.no_1_1) {
                    sharedViewModel.setData_1(1);
                }
            }
        });

        // 두 번째 라디오 버튼
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_2) {
                    sharedViewModel.setData_2(1);
                }
                else if (checkedId == R.id.no_1_2) {
                    sharedViewModel.setData_2(0);
                }
            }
        });

        // 세번째 라디오 버튼
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_3) {
                    sharedViewModel.setData_3(0);
                }
                else if (checkedId == R.id.no_1_3) {
                    sharedViewModel.setData_1(1);
                }
            }
        });
        return view;
    }
    private boolean areAllRadioGroupsChecked() {
        RadioGroup radioGroup1 = view.findViewById(R.id.radioGroup1);
        RadioGroup radioGroup2 = view.findViewById(R.id.radioGroup2);
        RadioGroup radioGroup3 = view.findViewById(R.id.radioGroup3);

        return isRadioButtonChecked(radioGroup1) && isRadioButtonChecked(radioGroup2) && isRadioButtonChecked(radioGroup3);
    }

    // 라디오 그룹 내의 라디오 버튼이 하나라도 체크되었는지 확인하는 메소드
    private boolean isRadioButtonChecked(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return checkedRadioButtonId != View.NO_ID;
    }
}
