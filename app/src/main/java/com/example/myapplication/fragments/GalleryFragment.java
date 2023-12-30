package com.example.myapplication.fragments;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.GalleryAdapter;
import com.example.myapplication.R;

import java.util.ArrayList;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

public class GalleryFragment extends Fragment {

    private ArrayList<String> imagePaths;
    private RecyclerView imagesRV;
    private GalleryAdapter galleryAdapter;

    public GalleryFragment() {
        // Required empty public constructor
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);
        imagesRV = view.findViewById(R.id.RVImages);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imagePaths = new ArrayList<>();
        requestPermissions();
        prepareRecyclerView();
    }

    private void requestPermissions() {
        int permissionResult = ContextCompat.checkSelfPermission(requireContext(), READ_EXTERNAL_STORAGE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && permissionResult != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{READ_EXTERNAL_STORAGE}, 200);
        } else {
            getImagePath();
        }
    }

    private void prepareRecyclerView() {
        galleryAdapter = new GalleryAdapter(requireContext(), imagePaths);
        GridLayoutManager manager = new GridLayoutManager(requireContext(), 4);
        imagesRV.setLayoutManager(manager);
        imagesRV.setAdapter(galleryAdapter);
    }

    public void getImagePath() {
        boolean galleryPresent = android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        if (galleryPresent) {
            final String[] columns = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
            final String orderBy = MediaStore.Images.Media._ID;

            Cursor cursor = requireContext().getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, orderBy);

            int count = cursor.getCount();
            for (int i = 0; i < count; i++) {
                cursor.moveToPosition(i);
                int dataColumnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                imagePaths.add(cursor.getString(dataColumnIndex));
            }
            galleryAdapter.notifyDataSetChanged();
            cursor.close();
        }
    }
}