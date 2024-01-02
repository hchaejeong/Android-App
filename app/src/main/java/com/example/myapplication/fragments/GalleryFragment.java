package com.example.myapplication.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.GalleryAdapter;
import com.example.myapplication.R;
import com.example.myapplication.TakePicture;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class GalleryFragment extends Fragment {

    private GalleryAdapter myAdapter;
    private ArrayList<String> imagePaths;
    private RecyclerView imagesRV;
    private static final int TAKE_PICTURE_REQUEST_CODE = 123;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        imagesRV = view.findViewById(R.id.RVImages);

        FloatingActionButton takePictureButton = view.findViewById(R.id.idImageAdd);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(requireActivity(), TakePicture.class);
                startActivityForResult(i, TAKE_PICTURE_REQUEST_CODE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TAKE_PICTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null && data.hasExtra("capturedImageUri")) {
                String capturedImageUri = data.getStringExtra("capturedImageUri");
                imagePaths.add(0, capturedImageUri);
                refreshGallery();
            }
        }
    }

    private void refreshGallery() {
        imagePaths.clear();
        getImagePath();

        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }

        Log.d("GalleryFragment", "Image paths: " + imagePaths.toString());
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagePaths = new ArrayList<>();
        requestPermissions();

        myAdapter = new GalleryAdapter(requireContext(), imagePaths);
        GridLayoutManager manager = new GridLayoutManager(requireContext(), calculateSpanCount());
        imagesRV.setLayoutManager(manager);
        imagesRV.setAdapter(myAdapter);
        imagesRV.setHasFixedSize(false);

        myAdapter.notifyDataSetChanged();
    }

    private int calculateSpanCount() {
        int totalImages = imagePaths.size();
        int defaultSpanCount = 4;

        int blankSpaces = defaultSpanCount - (totalImages % defaultSpanCount);
        int spanCount = Math.max(blankSpaces, defaultSpanCount);

        return spanCount;
    }

    private void requestPermissions() {
        int permissionResult = ContextCompat.checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE);
        if (permissionResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{READ_EXTERNAL_STORAGE}, 200);
        } else {
            refreshGallery();
        }
    }

    public void getImagePath() {
        boolean galleryPresent = android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (galleryPresent) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media.DATE_TAKEN + " DESC";

            Cursor cursor = requireContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

            if (cursor != null && cursor.getCount() > 0) {
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                while (cursor.moveToNext()) {
                    imagePaths.add(cursor.getString(dataColumnIndex));
                }

                myAdapter = new GalleryAdapter(requireContext(), imagePaths);
                GridLayoutManager manager = new GridLayoutManager(requireContext(), calculateSpanCount());
                imagesRV.setLayoutManager(manager);
                imagesRV.setAdapter(myAdapter);
                imagesRV.setHasFixedSize(false);

                myAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getImagePath();
            }
            else {
                Toast.makeText(requireContext(), "Permission Denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}