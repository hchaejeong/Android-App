package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.fragment.app.Fragment;

public class INTPFragment extends Fragment {

    public static INTPFragment newInstance() {
        return new INTPFragment();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.intp, container, false);

        Button home= rootView.findViewById(R.id.return_home_btn);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(PresentAdvisorFragment.newInstance());
            }
        });

        // 추가한 부분 : 이미지 누르면 쿠팡으로 이동
        ImageView shoppingImageView1 = rootView.findViewById(R.id.gift_1);
        shoppingImageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%B0%8D";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        ImageView shoppingImageView2 = rootView.findViewById(R.id.gift_2);
        shoppingImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%EC%8A%A4%EB%8F%84%EC%BF%A0%EC%B1%85";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        ImageView shoppingImageView3 = rootView.findViewById(R.id.gift_3);
        shoppingImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%EC%99%80%EC%9D%B8";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}