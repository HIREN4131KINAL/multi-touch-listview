package com.example.customlistview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

/**
 * Created by Android Developer on 26-May-16.
 */
public class FullImage extends Activity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_image);

        imageView = (ImageView)findViewById(R.id.imageView);

    }
}
