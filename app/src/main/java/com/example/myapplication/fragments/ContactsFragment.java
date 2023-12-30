package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myapplication.ContactsAdapter;
import com.example.myapplication.ContactsModule;
import com.example.myapplication.CreateNewContactActivity;
import com.example.myapplication.R;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.widget.Toast;

import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private ArrayList<ContactsModule> contactsList;
    RecyclerView recyclerView;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsList = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 100);
        } else {
            loadContacts();
        }

        FloatingActionButton addNewContactButton = rootView.findViewById(R.id.idFABAdd);

        addNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(requireActivity(), CreateNewContactActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 100) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadContacts();
            }
            else {
                Toast.makeText(requireContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void loadContacts() {
        Cursor cursor = requireContext().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " ASC");
        if (cursor != null && cursor.getCount() > 0) {
            int hasPhoneNumberIndex = cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);
            int contactIdIndex = cursor.getColumnIndex(ContactsContract.Contacts._ID);
            int displayNameIndex = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            while (cursor.moveToNext()) {
                int hasPhoneNumber = Integer.parseInt(cursor.getString(hasPhoneNumberIndex));
                //the phone number should be longer than 0
                if (hasPhoneNumber > 0) {
                    String contactId = cursor.getString(contactIdIndex);
                    String displayName = cursor.getString(displayNameIndex);
                    Cursor phoneCursor = requireContext().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                            new String[]{contactId},
                            null
                    );

                    if (phoneCursor != null && phoneCursor.moveToNext()) {
                        int phoneNumberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        String phoneNumber = phoneCursor.getString(phoneNumberIndex);
                        ContactsModule myContact = new ContactsModule(displayName, phoneNumber);
                        contactsList.add(myContact);
                    }
                    phoneCursor.close();
                }
            }

            //initialize adapter for recyclerView
            ContactsAdapter myAdapter = new ContactsAdapter(requireContext(), contactsList);
            recyclerView.setAdapter(myAdapter);
            myAdapter.notifyDataSetChanged();
        }
        else {
            Toast.makeText(requireContext(), "No contacts found", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}