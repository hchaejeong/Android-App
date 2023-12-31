package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Tap3_Page1 extends AppCompatActivity{
    private int e_count=0;
    private int i_count=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tap3_page1);

        Button before1 = findViewById(R.id.before1);
        before1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        Button after1 = findViewById(R.id.after1);
        after1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (areAllRadioGroupsChecked()) {
                    Intent intent = new Intent (getApplicationContext(), Tap3_Page2.class);
                    intent.putExtra("e_count",e_count);
                    intent.putExtra("i_count",i_count);
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
                    i_count+=1;
                }
                else if (checkedId == R.id.no_1_1) {
                    e_count+=1;
                }
            }
        });

        // 두 번째 라디오 버튼
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_2) {
                    i_count+=1;
                }
                else if (checkedId == R.id.no_1_2) {
                    e_count +=1;
                }
            }
        });

        // 세번째 라디오 버튼
        radioGroup3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.yes_1_3) {
                    i_count++;
                }
                else if (checkedId == R.id.no_1_3) {
                    e_count++;
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
