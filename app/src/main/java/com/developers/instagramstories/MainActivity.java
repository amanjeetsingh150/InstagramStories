package com.developers.instagramstories;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements SegmentedProgressBar.updatePhotoListener {

    private int[] myImages = new int[]{R.drawable.arrow_one, R.drawable.arrow_two};
    private SegmentedProgressBar segmentedProgressBar;
    private static ImageView backgroundImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        segmentedProgressBar = (SegmentedProgressBar) findViewById(R.id.segmented_progress);
        backgroundImage = (ImageView) findViewById(R.id.background_image_view);
    }


    @Override
    public void changePhoto(int number) {
        if(number<2) {
            backgroundImage.setImageResource(myImages[number]);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        backgroundImage=null;
    }
}
