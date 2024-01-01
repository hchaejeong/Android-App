package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;

public class ImageDetailActivity extends AppCompatActivity {
    String imagePath;
    private ImageView imageView;
    private ScaleGestureDetector scaleGestureDetector;
    private float mScaleFactor = 1.0f;
    private Button btnMakeBlackWhite;
    private Button btnMakeColor;
    private Button btnMakeBright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        imagePath = getIntent().getStringExtra("imgPath");
        imageView = findViewById(R.id.idIVImage);
        //using this for zoom in and zoom out of the image
        scaleGestureDetector = new ScaleGestureDetector(this, new ScaleListener());

        File imageFile = new File(imagePath);

        if (imageFile.exists()) {
            Picasso.get().load(imageFile).placeholder(R.drawable.ic_launcher_background).into(imageView);
        }

        btnMakeBlackWhite = findViewById(R.id.btnMakeBlackWhite);
        btnMakeColor = findViewById(R.id.btnMakeColor);
        btnMakeBright = findViewById(R.id.btnMakeBright);

        btnMakeBlackWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyBlackAndWhiteEffect();
            }
        });
        btnMakeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyColorEffect();
            }
        });
        btnMakeBright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                applyBrightness();
            }
        });
    }

    private void applyBlackAndWhiteEffect() {
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0);

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        imageView.invalidate();
    }

    private void applyColorEffect() {
        imageView.setColorFilter(null);
        imageView.invalidate();
    }

    private void applyBrightness() {
        float brightnessValue = 20.0f;

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.set(new float[]{
                1, 0, 0, 0, brightnessValue,
                0, 1, 0, 0, brightnessValue,
                0, 0, 1, 0, brightnessValue,
                0, 0, 0, 1, 0
        });

        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(colorMatrix);
        imageView.setColorFilter(filter);
        imageView.invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        scaleGestureDetector.onTouchEvent(motionEvent);
        return true;
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
            mScaleFactor *= scaleGestureDetector.getScaleFactor();
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f));
            imageView.setScaleX(mScaleFactor);
            imageView.setScaleY(mScaleFactor);
            return true;
        }
    }
}