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

public class ENTJFragment extends Fragment {

    public static ENTJFragment newInstance() {
        return new ENTJFragment();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.entj, container, false);

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
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%EC%9E%90%EA%B8%B0%EA%B3%84%EB%B0%9C+%EC%84%9C%EC%A0%81";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        ImageView shoppingImageView2 = rootView.findViewById(R.id.gift_2);
        shoppingImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%EB%B3%B4%EB%93%9C%EA%B2%8C%EC%9E%84";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        ImageView shoppingImageView3 = rootView.findViewById(R.id.gift_3);
        shoppingImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String shoppingAppPageUrl ="https://m.coupang.com/np/search?q=%EB%A7%8C%EB%85%84%ED%95%84";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppPageUrl));
                startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}