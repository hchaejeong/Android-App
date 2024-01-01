package com.example.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import android.widget.EditText;
import android.widget.Toast;

import android.content.pm.PackageManager;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MenuItemCompat;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ContactsFragment extends Fragment {
    private ArrayList<ContactsModule> contactsList;
    private ArrayList<ContactsModule> filterList;
    private RecyclerView recyclerView;
    private ContactsAdapter myAdapter;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true); // This line is important to enable the options menu
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);

        contactsList = new ArrayList<>();
        filterList = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        myAdapter = new ContactsAdapter(requireContext(), contactsList, recyclerView);
        recyclerView.setAdapter(myAdapter);

        Log.d("CreateView", "Create view is called");

        EditText searchEditText = rootView.findViewById(R.id.editText);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.gray));
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = searchEditText.getText().toString();
                filterList.clear();

                if (searchText.equals("")) {
                    myAdapter.filterList(contactsList);
                } else {
                    for (int a = 0; a < contactsList.size(); a++) {
                        if (contactsList.get(a).getName().toLowerCase().contains(searchText.toLowerCase())) {
                            filterList.add(contactsList.get(a));
                        }
                        myAdapter.filterList(filterList);
                    }
                }
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.READ_CONTACTS}, 100);
            } else {
                if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.WRITE_CONTACTS}, 101);
                } else {
                    loadContacts();
                }
            }
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_contacts_menu, menu);

        MenuItem searchViewItem = menu.findItem(R.id.searchName);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void filter(String text) {
        Log.d("Filter", "Filtering with text:" + text);
        ArrayList<ContactsModule> filteredlist = new ArrayList<>();
        for (ContactsModule item : contactsList) {
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(requireContext(), "No matching contact found", Toast.LENGTH_SHORT).show();
        } else {
            myAdapter.filterList(filteredlist);
        }
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
                    int contactId = Integer.parseInt(cursor.getString(contactIdIndex));
                    String displayName = cursor.getString(displayNameIndex);
                    Cursor phoneCursor = requireContext().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                            new String[]{String.valueOf(contactId)},
                            null
                    );

                    if (phoneCursor != null && phoneCursor.moveToNext()) {
                        int phoneNumberIndex = phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                        String phoneNumber = phoneCursor.getString(phoneNumberIndex);
                        ContactsModule myContact = new ContactsModule(contactId, displayName, phoneNumber);
                        contactsList.add(myContact);
                    }
                    phoneCursor.close();
                }
            }
            myAdapter = new ContactsAdapter(requireContext(), contactsList, recyclerView);
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