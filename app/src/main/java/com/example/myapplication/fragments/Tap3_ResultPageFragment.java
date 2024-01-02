package com.example.myapplication.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.SharedViewModel;

import androidx.lifecycle.ViewModelProvider;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

public class Tap3_ResultPageFragment extends Fragment {
    private KonfettiView viewKonfetti;
    private SharedViewModel sharedViewModel;
    private View view;
    private int ei;
    private int sn;
    private int tf;
    private int jp;
    private int question1,question2,question3,question4,question5,question6;
    private int question7,question8,question9,question10,question11,question12;

    public static Tap3_ResultPageFragment newInstance() {
        return new Tap3_ResultPageFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_tap3_resultpage, container, false);

        viewKonfetti =view.findViewById(R.id.viewKonfetti);

        question1 = sharedViewModel.getData_1();
        question2 = sharedViewModel.getData_2();
        question3 = sharedViewModel.getData_3();
        question4 = sharedViewModel.getData_4();
        question5 = sharedViewModel.getData_5();
        question6 = sharedViewModel.getData_6();
        question7 = sharedViewModel.getData_7();
        question8 = sharedViewModel.getData_8();
        question9 = sharedViewModel.getData_9();
        question10 = sharedViewModel.getData_10();
        question11 = sharedViewModel.getData_11();
        question12 = sharedViewModel.getData_12();


        ei=question6+question8+question12;
        sn=question2+question7+question10;
        tf=question4+question9+question11;
        jp=question1+question3+question5;



        Button res_btn = view.findViewById(R.id.result_btn);
        res_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation explosionAnimation = AnimationUtils.loadAnimation(Tap3_ResultPageFragment.this.getContext(), R.anim.firework_animation);

                explosionAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        viewKonfetti.build()
                                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                                .setDirection(0.0, 359.0)
                                .setSpeed(1f, 5f)
                                .setFadeOutEnabled(true)
                                .setTimeToLive(2000L)
                                .addShapes(Shape.RECT, Shape.CIRCLE)
                                .addSizes(new Size(12, 5))
                                .setPosition(-50f, viewKonfetti.getWidth() + 50f, -50f, -50f)
                                .streamFor(300, 5000L);

                    }
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        navigateToNextFragment();

                    }
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
                // 버튼에 애니메이션 설정
                res_btn.startAnimation(explosionAnimation);

            }
        });
        return view;
    }
    private void navigateToNextFragment() {
        // 여기에 다음 Fragment로의 전환 코드를 작성
        if (ei>2 && sn>2 && tf>2 && jp>2){   //INTP
            ((MainActivity)getActivity()).replaceFragment(INTPFragment.newInstance());
        }
        else if (ei>2 && sn>2 && tf>2 && jp<2){ //INTJ
            ((MainActivity)getActivity()).replaceFragment(INTJFragment.newInstance());
        }
        else if (ei>2 && sn>2 && tf<2 && jp>2){ //INFP
            ((MainActivity)getActivity()).replaceFragment(INFPFragment.newInstance());
        }
        else if (ei>2 && sn>2 && tf<2 && jp<2){  //INFJ
            ((MainActivity)getActivity()).replaceFragment(INFJFragment.newInstance());
        }

        ////////////////////////////////////////////////////////////
        else if (ei>2 && sn<2 && tf>2 && jp>2){ //ISTP
            ((MainActivity)getActivity()).replaceFragment(ISTPFragment.newInstance());
        }
        else if (ei>2 && sn<2 && tf>2 && jp<2){ //ISTJ
            ((MainActivity)getActivity()).replaceFragment(ISTJFragment.newInstance());
        }
        else if (ei>2 && sn<2 && tf<2 && jp>2){ //ISFP
            ((MainActivity)getActivity()).replaceFragment(ISFPFragment.newInstance());
        }
        else if (ei>2 && sn<2 && tf<2 && jp<2){ //ISFJ
            ((MainActivity)getActivity()).replaceFragment(ISFJFragment.newInstance());
        }
        /////////////////////////////////////////////////////////////////
        else if (ei<2 && sn>2 && tf>2 && jp>2){ //ENTP
            ((MainActivity)getActivity()).replaceFragment(ENTPFragment.newInstance());
        }
        else if (ei<2 && sn>2 && tf>2 && jp<2){ //ENTJ
            ((MainActivity)getActivity()).replaceFragment(ENTJFragment.newInstance());
        }
        else if (ei<2 && sn>2 && tf<2 && jp>2){ //ENFP
            ((MainActivity)getActivity()).replaceFragment(ENFPFragment.newInstance());
        }
        else if (ei<2 && sn>2 && tf<2 && jp<2){ //ENFJ
            ((MainActivity)getActivity()).replaceFragment(ENFJFragment.newInstance());
        }

        ////////////////////////////////////////////////////////////
        else if (ei<2 && sn<2 && tf>2 && jp>2){ //ESTP
            ((MainActivity)getActivity()).replaceFragment(ESTPFragment.newInstance());
        }
        else if (ei<2 && sn<2 && tf>2 && jp<2){ //ESTJ
            ((MainActivity)getActivity()).replaceFragment(ESTJFragment.newInstance());
        }
        else if (ei<2 && sn<2 && tf<2 && jp>2){ //ESFP
            ((MainActivity)getActivity()).replaceFragment(ESFPFragment.newInstance());
        }
        else if (ei<2 && sn<2 && tf<2 && jp<2){ //ESFJ
            ((MainActivity)getActivity()).replaceFragment(ESFJFragment.newInstance());
        }
    }
}
