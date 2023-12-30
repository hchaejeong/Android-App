package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ImagesViewHolder> {
    private Context context;
    private ArrayList<String> imagePathArrayList;

    public GalleryAdapter(Context context, ArrayList<String> imagePathArrayList) {
        this.context = context;
        this.imagePathArrayList = imagePathArrayList;
    }

    @Override
    public ImagesViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list, parent, false);
        return new GalleryAdapter.ImagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder, int position) {
        // on below line we are getting the file from the
        // path which we have stored in our list.
        File imgFile = new File(imagePathArrayList.get(holder.getAdapterPosition()));

        // on below line we are checking if the file exists or not.
        if (imgFile.exists()) {

            // if the file exists then we are displaying that file in our image view using picasso library.
            Picasso.get().load(imgFile).placeholder(R.drawable.ic_launcher_background).into(holder.imageIV);

            // on below line we are adding click listener to our item of recycler view.
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are creating a new intent
                    Intent i = new Intent(context, ImageDetailActivity.class);
                    // on below line we are passing the image path to our new activity.
                    i.putExtra("imgPath", imagePathArrayList.get(holder.getAdapterPosition()));
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        // this method returns the size of recyclerview
        return imagePathArrayList.size();
    }

    public class ImagesViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views.
        private ImageView imageIV;

        public ImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our views with their ids.
            imageIV = itemView.findViewById(R.id.idIVImage);
        }
    }
}
