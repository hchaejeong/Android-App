package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    //creating variables for context and array list.
    private Context context;
    private ArrayList<ContactsModule> contactsArrayList;
    LayoutInflater inflater;

    //creating a constructor.
    public ContactsAdapter(Context context, ArrayList<ContactsModule> arrayList) {
        this.context = context;
        this.contactsArrayList = arrayList;
    }

    @Override
    public ContactsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
        return new ContactsAdapter.ContactsViewHolder(view);
    }

    public void filterList(ArrayList<ContactsModule> filterList) {
        contactsArrayList = filterList;
        notifyDataSetChanged();
        Log.d("Adapter", "Dataset filtered, new size: " + getItemCount());
    }

    @Override
    public void onBindViewHolder(ContactsViewHolder holder, int position) {
        ContactsModule myContact = contactsArrayList.get(position);
        holder.contactName.setText(myContact.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ViewContactDetails.class);
                i.putExtra("name", myContact.getName());
                i.putExtra("contact", myContact.getContactNumber());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactsArrayList.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        private ImageView contactImage;
        private TextView contactName;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            contactImage = itemView.findViewById(R.id.idIVContact);
            contactName = itemView.findViewById(R.id.idTVContactName);
        }
    }
}