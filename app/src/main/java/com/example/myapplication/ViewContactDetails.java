package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

public class ViewContactDetails extends AppCompatActivity {
    private String contactName, contactNumber;
    private TextView contactTV, nameTV;
    private ImageView contactIV, callIV, messageIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact_details);

        contactName = getIntent().getStringExtra("name");
        contactNumber = getIntent().getStringExtra("contact");

        nameTV = findViewById(R.id.idTVName);
        contactIV = findViewById(R.id.idIVContact);
        contactTV = findViewById(R.id.idTVPhone);
        callIV = findViewById(R.id.idIVCall);
        messageIV = findViewById(R.id.idIVMessage);

        nameTV.setText(contactName);
        contactTV.setText(contactNumber);
    }
}