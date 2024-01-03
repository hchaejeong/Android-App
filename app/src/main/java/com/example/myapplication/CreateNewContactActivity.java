package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.TextUtils;
import android.widget.Toast;

import android.os.Bundle;

import java.util.ArrayList;

public class CreateNewContactActivity extends AppCompatActivity {
    private EditText nameEdt, phoneEdt;
    private Button addContactEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_contact);

        nameEdt = findViewById(R.id.idEdtName);
        phoneEdt = findViewById(R.id.idEdtPhoneNumber);
        addContactEdt = findViewById(R.id.idBtnAddContact);
        addContactEdt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();

                if (TextUtils.isEmpty(name) && TextUtils.isEmpty(phone)) {
                    Toast.makeText(CreateNewContactActivity.this, "Please enter the data in all fields. ", Toast.LENGTH_SHORT).show();
                } else {
                    //calling a method to add contact.
                    addContact(name, phone);
                }
            }
        });
    }

    private boolean checkPermissionsAndData(String name, String phone) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 102);
                return false;
            }
        }
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(this, "Please enter valid data in all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void addContact(String name, String phone) {
        ArrayList<ContentProviderOperation> contacts = new ArrayList<>();
        int contactIndex = contacts.size();

        contacts.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI).
                withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null).
                withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).
                build());

        if (name!= null && !name.trim().equals("")) {
            contacts.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).
                withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex).
                withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).
                withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name).
                build());
        }
        if (phone!= null && !phone.trim().equals("")) {
            contacts.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).
                    withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, contactIndex).
                    withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE).
                    withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, phone).
                    withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).
                    build());
        }


        if(contacts.size() > 0) {
            try {
                getContentResolver().applyBatch(ContactsContract.AUTHORITY, contacts);
                Toast.makeText(this, "Contact has been added.", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(CreateNewContactActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error adding contact.", Toast.LENGTH_SHORT).show();
            }
        }

        /*
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        contactIntent
                .putExtra(ContactsContract.Intents.Insert.NAME, name)
                .putExtra(ContactsContract.Intents.Insert.PHONE, phone);
        startActivityForResult(contactIntent, 1);
        */
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                String name = nameEdt.getText().toString();
                String phone = phoneEdt.getText().toString();
                if(checkPermissionsAndData(name, phone)) {
                    addContact(name, phone);
                }
            }
            else {
                Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}