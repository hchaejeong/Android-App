package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.myapplication.fragments.PresentAdvisorFragment;

public class ENFJ extends AppCompatActivity{

    PresentAdvisorFragment presentAdvisorFragment = new PresentAdvisorFragment();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enfj);


        Button home = findViewById(R.id.return_home_btn);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),MainActivity.class);
                intent.putExtra("fragmentTag","yourFragmentTag");
                startActivity(intent);
            }
        });
    }
}
