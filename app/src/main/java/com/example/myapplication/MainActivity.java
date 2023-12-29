package com.example.myapplication;

import android.os.Bundle;
import android.view.MenuItem;

import android.os.Build;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.myapplication.fragments.ContactsFragment;
import com.example.myapplication.fragments.GalleryFragment;
import com.example.myapplication.fragments.PresentAdvisorFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ContactsFragment contactsFragment = new ContactsFragment();
    GalleryFragment galleryFragment = new GalleryFragment();
    PresentAdvisorFragment presentAdvisorFragment = new PresentAdvisorFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}

