package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.widget.TextView;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

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

        loadContactImage();

        callIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeCall(contactNumber);
            }
        });
        messageIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage(contactNumber);
            }
        });
    }

    private void sendMessage(String contactNumber) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms: " + contactNumber));
        intent.putExtra("sms_body", "");

        startActivity(intent);
    }

    private void makeCall(String contactNumber) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel: " + contactNumber));
        if (ActivityCompat.checkSelfPermission(ViewContactDetails.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        startActivity(callIntent);
    }

    private void loadContactImage() {
        Uri contactUri = getContactUri(contactNumber);
        Bitmap contactImageBitmap = getContactPhoto(contactUri);

        if (contactImageBitmap != null) {
            contactIV.setImageBitmap(contactImageBitmap);
        } else {
            // If no contact image is available, set a default image
            contactIV.setImageResource(R.drawable.baseline_account_circle_24);
        }
    }

    private Uri getContactUri(String phoneNumber) {
        Uri lookupUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI, Uri.encode(phoneNumber));
        Cursor cursor = getContentResolver().query(lookupUri, new String[]{ContactsContract.PhoneLookup._ID}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int contactIdIndex = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
            long contactId = cursor.getLong(contactIdIndex);
            cursor.close();
            return ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        }

        if (cursor != null) {
            cursor.close();
        }

        return null;
    }

    private Bitmap getContactPhoto(Uri contactUri) {
        InputStream inputStream = ContactsContract.Contacts.openContactPhotoInputStream(getContentResolver(), contactUri, true);

        if (inputStream != null) {
            try {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inPreferredConfig = Bitmap.Config.ARGB_8888;
                return BitmapFactory.decodeStream(inputStream, null, options);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

}