package com.developers.instagramstories;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Amanjeet Singh on 2/8/17.
 */

public class SegmentedProgressBar extends View {

    private static final String TAG = SegmentedProgressBar.class.getSimpleName();
    private Paint paint, fillingPaint;
    private int startX = 0, diff = 20, i = 0, fillStartX = 0;
    private Path path, fillingPath;
    private int count;
    private int noOfPhoto=0;
    private static updatePhotoListener IUpdatePhotoListener;

    Runnable animator = new Runnable() {
        @Override
        public void run() {
            for (int j = 0; j < count; j++) {
                if (i <= getWidth() / count) {
                    i++;
                } else {
                    i = 0;
                    fillStartX += getWidth() / count + diff;
                    if(noOfPhoto<=count-1) {
                        noOfPhoto++;
                        IUpdatePhotoListener.changePhoto(noOfPhoto);
                    }

                }
                invalidate();
            }
            postDelayed(this, 30);
        }
    };


    public SegmentedProgressBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint = new Paint();
        fillingPaint = new Paint();
        path = new Path();
        fillingPath = new Path();

        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);

        fillingPaint.setStrokeWidth(10);
        fillingPaint.setStyle(Paint.Style.STROKE);
        fillingPaint.setColor(Color.RED);

        TypedArray array = context.getTheme()
                .obtainStyledAttributes(attrs, R.styleable.SegmentedProgressBar, 0, 0);
        try {
            count = array.getInteger(R.styleable.SegmentedProgressBar_count, 3);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            array.recycle();
        }



        post(animator);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawRect(canvas);
        drawFillingRect(canvas);
    }

    private void drawRect(Canvas canvas) {
        for (int j = 0; j < count; j++) {
            path.moveTo(startX, 0);
            path.lineTo(startX + getWidth() / count, 0);
            startX += getWidth() / count + diff;
            path.close();
            canvas.drawPath(path, paint);
            if (j == count-1) {
                startX = 0;
            }
        }

    }

    private void drawFillingRect(Canvas canvas) {
        for (int j = 0; j < count; j++) {
            fillingPath.moveTo(fillStartX, 0);
            fillingPath.lineTo(fillStartX + i, 0);
            canvas.drawPath(fillingPath, fillingPaint);
        }
    }

    public interface updatePhotoListener {
        void changePhoto(int  number);
    }

    public static void registerUpdateListener(updatePhotoListener IUpdatePhotoListener){
        SegmentedProgressBar.IUpdatePhotoListener=IUpdatePhotoListener;
    }

}
