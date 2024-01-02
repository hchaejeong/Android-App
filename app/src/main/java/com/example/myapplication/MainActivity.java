package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import android.os.Build;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.fragments.ContactsFragment;
import com.example.myapplication.fragments.GalleryFragment;
import com.example.myapplication.fragments.PresentAdvisorFragment;
import com.example.myapplication.fragments.Tap3_Page1Fragment;
import com.example.myapplication.fragments.Tap3_Page2Fragment;
import com.example.myapplication.fragments.Tap3_Page3Fragment;
import com.example.myapplication.fragments.Tap3_Page4Fragment;
import com.example.myapplication.fragments.Tap3_ResultPageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private SharedViewModel sharedViewModel;
    ContactsFragment contactsFragment = new ContactsFragment();
    GalleryFragment galleryFragment = new GalleryFragment();
    PresentAdvisorFragment presentAdvisorFragment = new PresentAdvisorFragment();

    //tap3 fragment 페이지들
    Tap3_Page1Fragment tap3_page1fragment = new Tap3_Page1Fragment();
    Tap3_Page2Fragment tap3_page2fragment = new Tap3_Page2Fragment();
    Tap3_Page3Fragment tap3_page3fragment = new Tap3_Page3Fragment();
    Tap3_Page4Fragment tap3_page4fragment = new Tap3_Page4Fragment();
    Tap3_ResultPageFragment tap3_resultpagefragment = new Tap3_ResultPageFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewModel을 사용하기 위한 부분
        sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);
        sharedViewModel.setData_1(0);
        sharedViewModel.setData_2(0);
        sharedViewModel.setData_3(0);
        sharedViewModel.setData_4(0);
        sharedViewModel.setData_5(0);
        sharedViewModel.setData_6(0);
        sharedViewModel.setData_7(0);
        sharedViewModel.setData_8(0);
        sharedViewModel.setData_9(0);
        sharedViewModel.setData_10(0);
        sharedViewModel.setData_11(0);
        sharedViewModel.setData_12(0);

        //getSupportFragmentManager().beginTransaction().replace(R.id.container, Tap3_Page1Fragment.newInstance()).commit();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, contactsFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId() == R.id.contacts) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, contactsFragment).commit();
                } else if (item.getItemId() == R.id.gallery) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, galleryFragment).commit();
                } else if (item.getItemId() == R.id.present_advisor) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, presentAdvisorFragment).commit();
                }
                return true;
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // Check which fragment is currently active and forward the result to it
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
        if (fragment instanceof ContactsFragment) {
            ((ContactsFragment) fragment).onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment).commit();
    }

}

