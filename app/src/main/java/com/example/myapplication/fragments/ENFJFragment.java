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


public class ENFJFragment extends Fragment {

    public static ENFJFragment newInstance() {
        return new ENFJFragment();
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.enfj, container, false);

        Button home= rootView.findViewById(R.id.return_home_btn);
        home.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                ((MainActivity)getActivity()).replaceFragment(PresentAdvisorFragment.newInstance());
            }
        });

        ImageView shoppingImageView = rootView.findViewById(R.id.gift_1);

        shoppingImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                openShoppingApp();
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void openShoppingApp() {
        // 쇼핑앱의 패키지명을 확인하고, 아래 "com.android.chrome" 대신에 해당 패키지명을 넣어주세요.
        String shoppingAppPackage = "com.coupang.mobile&hl=ko&gl=US";

        // 쇼핑앱의 마켓 URL을 확인하고, 아래 "https://play.google.com/store/apps/details?id=com.android.chrome" 대신에 해당 URL을 넣어주세요.
        String shoppingAppMarketUrl = "https://play.google.com/store/apps/details?id=com.coupang.mobile&hl=ko&gl=US";

        Intent intent = getActivity().getPackageManager().getLaunchIntentForPackage(shoppingAppPackage);

        if (intent != null) {
            // 쇼핑앱이 설치되어 있으면 앱을 엽니다.
            startActivity(intent);
        } else {
            // 쇼핑앱이 설치되어 있지 않으면 마켓으로 이동합니다.
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(shoppingAppMarketUrl));
            startActivity(intent);
        }
    }
}