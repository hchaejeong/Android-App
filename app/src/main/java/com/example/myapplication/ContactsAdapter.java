package com.example.myapplication;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    //creating variables for context and array list.
    private Context context;
    private ArrayList<ContactsModule> contactsArrayList;
    private RecyclerView recyclerView;

    //creating a constructor.
    public ContactsAdapter(Context context, ArrayList<ContactsModule> arrayList, RecyclerView recyclerView) {
        this.context = context;
        this.contactsArrayList = arrayList;
        this.recyclerView = recyclerView;
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

    //public void restoreContacts(String )

    private void removeContact(ContactsModule contact) {
        int position = contactsArrayList.indexOf(contact);
        contactsArrayList.remove(position);
        notifyItemRemoved(position);

        removeContactFromDevice(contact);
    }

    private void removeContactFromDevice(ContactsModule contact) {
        long contactId = contact.getId();
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactId);
        ContentResolver resolver = context.getContentResolver();

        try {
            int rowsDeleted = resolver.delete(contactUri, null, null);
            if (rowsDeleted > 0) {

                Toast.makeText(context, "Contact deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Failed to delete contact", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setSwipeToDelete() {
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                ContactsModule contactsModule = contactsArrayList.get(position);
                removeContact(contactsModule);
                notifyItemRemoved(position);
                notifyItemRangeRemoved(position, getItemCount() - position);
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                setDeleteIcon(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void setDeleteIcon(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        View itemView = viewHolder.itemView;
        float width = (float) itemView.getWidth();
        //float alpha = 1.0f - Math.abs(dX) / width;

        Paint paint = new Paint();
        paint.setColor(Color.RED);
        //paint.setAlpha((int) (alpha * 255));
        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), (float) itemView.getLeft() + dX, (float) itemView.getBottom(), paint);

        Drawable deleteIcon = ContextCompat.getDrawable(context, R.drawable.baseline_delete_24);
        if (deleteIcon != null) {
            int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
            int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

            float iconPosition = Math.min(dX / 2, width / 2);
            int iconLeft = (int) (itemView.getLeft() + iconPosition);
            int iconRight = iconLeft + deleteIcon.getIntrinsicWidth();
            deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
            deleteIcon.draw(c);
        }
    }
    /*
    private final ItemTouchHelper.Callback touchHelperCallback = new ItemTouchHelper.Callback() {
        //private static final float SWIPE_THRESHOLD = 0.15f;
        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int swipeFlags = ItemTouchHelper.RIGHT;
            return makeMovementFlags(0, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            int position = viewHolder.getAbsoluteAdapterPosition();

            if (position >= 0 && position < contactsArrayList.size()) {
                ContactsModule contactToDelete = contactsArrayList.get(position);
                removeContact(contactToDelete);
                notifyItemRemoved(position);
            } else {
                Log.e("ContactsAdapter", "Invalid position: " + position);
            }
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                View itemView = viewHolder.itemView;
                float width = (float) itemView.getWidth();
                //float alpha = 1.0f - Math.abs(dX) / width;

                Paint paint = new Paint();
                paint.setColor(Color.RED);
                //paint.setAlpha((int) (alpha * 255));
                c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), (float) itemView.getLeft() + dX, (float) itemView.getBottom(), paint);

                Drawable deleteIcon = ContextCompat.getDrawable(context, R.drawable.baseline_delete_24);
                if (deleteIcon != null) {
                    int iconTop = itemView.getTop() + (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
                    int iconBottom = iconTop + deleteIcon.getIntrinsicHeight();

                    float iconPosition = Math.min(dX / 2, width / 2);
                    int iconLeft = (int) (itemView.getLeft() + iconPosition);
                    int iconRight = iconLeft + deleteIcon.getIntrinsicWidth();
                    deleteIcon.setBounds(iconLeft, iconTop, iconRight, iconBottom);
                    deleteIcon.draw(c);
                }
            }
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

     */

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