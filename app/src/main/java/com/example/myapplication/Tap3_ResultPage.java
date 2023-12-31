package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Tap3_ResultPage extends AppCompatActivity{
    private int j=0;
    private int p=0;
    private int t=0;
    private int f=0;
    private int e=0;
    private int i=0;
    private int n=0;
    private int s=0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap3_resultpage);

        Intent intent = getIntent();
        e = intent.getIntExtra("e_count",0);
        i = intent.getIntExtra("i_count",0);
        n = intent.getIntExtra("n_count",0);
        s = intent.getIntExtra("s_count",0);
        t = intent.getIntExtra("t_count",0);
        f = intent.getIntExtra("f_count",0);
        j = intent.getIntExtra("j_count",0);
        p = intent.getIntExtra("p_count",0);


        Button res_btn = findViewById(R.id.result_btn);
        res_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (e>i && n>s && t<f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ENFJ.class);
                    startActivity(intent);
                }
                else if (e>i && n>s && t<f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ENFP.class);
                    startActivity(intent);
                }
                else if (e>i && n>s && t>f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ENTJ.class);
                    startActivity(intent);
                }
                else if (e>i && n>s && t>f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ENTP.class);
                    startActivity(intent);
                }

                else if (e>i && n<s && t<f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ESFJ.class);
                    startActivity(intent);
                }
                else if (e>i && n<s && t<f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ESFP.class);
                    startActivity(intent);
                }
                else if (e>i && n<s && t>f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ESTJ.class);
                    startActivity(intent);
                }
                else if (e>i && n<s && t>f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ESTP.class);
                    startActivity(intent);
                }


                else if (e<i && n>s && t<f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),INFJ.class);
                    startActivity(intent);
                }
                else if (e<i && n>s && t<f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),INFP.class);
                    startActivity(intent);
                }
                else if (e<i && n>s && t>f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),INTJ.class);
                    startActivity(intent);
                }
                else if (e<i && n>s && t>f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),INTP.class);
                    startActivity(intent);
                }

                else if (e<i && n<s && t<f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ISFJ.class);
                    startActivity(intent);
                }
                else if (e<i && n<s && t<f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ISFP.class);
                    startActivity(intent);
                }
                else if (e<i && n<s && t>f && j>p) {
                    Intent intent = new Intent (getApplicationContext(),ISTJ.class);
                    startActivity(intent);
                }
                else if (e<i && n<s && t>f && j<p) {
                    Intent intent = new Intent (getApplicationContext(),ISTP.class);
                    startActivity(intent);
                }
            }
        });
    }
}
