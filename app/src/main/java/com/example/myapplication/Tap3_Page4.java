package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Tap3_Page4 extends AppCompatActivity{
    private int j_count=0;
    private int p_count=0;
    private int t=0;
    private int f=0;
    private int e=0;
    private int i=0;
    private int n=0;
    private int s=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap3_page4);

        Intent intent = getIntent();
        e = intent.getIntExtra("e_count",0);
        i = intent.getIntExtra("i_count",0);
        n = intent.getIntExtra("n_count",0);
        s = intent.getIntExtra("s_count",0);
        t = intent.getIntExtra("t_count",0);
        f = intent.getIntExtra("f_count",0);

        Button before1 = findViewById(R.id.before1);
        before1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(), Tap3_Page3.class);
                startActivity(intent);
            }
        });

        Button after1 = findViewById(R.id.after1);
        after1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areAllRadioGroupsChecked()) {
                    Intent intent = new Intent (getApplicationContext(), Tap3_ResultPage.class);
                    intent.putExtra("e_count",e);
                    intent.putExtra("i_count",i);
                    intent.putExtra("n_count",n);
                    intent.putExtra("s_count",s);
                    intent.putExtra("t_count",t);
                    intent.putExtra("f_count",f);
                    intent.putExtra("j_count",j_count);
                    intent.putExtra("p_count",p_count);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(),"선택되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // 첫 번째 질문과 라디오 그룹
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        // 두 번째 질문과 라디오 그룹
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        // 세 번째 질문과 라디오 그룹
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);

        // 첫 번째 라디오 버튼
        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_1) {
                    j_count+=1;
                }
                else if (checkedId == R.id.no_1_1) {
                    p_count+=1;
                }
            }
        });

        // 두 번째 라디오 버튼
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_2) {
                    j_count+=1;
                }
                else if (checkedId == R.id.no_1_2) {
                    p_count +=1;
                }
            }
        });

        // 세번째 라디오 버튼
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_3) {
                    j_count++;
                }
                else if (checkedId == R.id.no_1_3) {
                    p_count++;
                }
            }
        });

    }
    private boolean areAllRadioGroupsChecked() {
        RadioGroup radioGroup1 = findViewById(R.id.radioGroup1);
        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioGroup radioGroup3 = findViewById(R.id.radioGroup3);

        return isRadioButtonChecked(radioGroup1) && isRadioButtonChecked(radioGroup2) && isRadioButtonChecked(radioGroup3);
    }

    // 라디오 그룹 내의 라디오 버튼이 하나라도 체크되었는지 확인하는 메소드
    private boolean isRadioButtonChecked(RadioGroup radioGroup) {
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        return checkedRadioButtonId != View.NO_ID;
    }
}
