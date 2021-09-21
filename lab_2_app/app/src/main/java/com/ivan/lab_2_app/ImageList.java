package com.ivan.lab_2_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImageList extends RecyclerView {

    private List<Drawable> _drawables = null;
    private List<ImageItem> _imagesSource = new ArrayList<ImageItem>();
    private int _current = 0;
    private int _deltaX = 0, _deltaY = 0;

    private Resources _resources = null;

    public ImageList(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    public void Init(ImageItem[] _images, Resources resources){

        _imagesSource.addAll(Arrays.asList(_images));
        _resources = resources;
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        if(_imagesSource != null){

            double res = 1 - Math.abs(_deltaX) / 600.0;

            @SuppressLint("UseCompatLoadingForDrawables")
            Drawable currentDrawable = _resources.getDrawable(_imagesSource.get(_current).getImageUri());

            currentDrawable.setAlpha((int) (res * 255));
            currentDrawable.setBounds(_deltaX, _deltaY, _deltaX + getWidth(), _deltaY + getHeight());
            //currentDrawable.
            currentDrawable.draw(c);


            Paint textPaint = new Paint();
            textPaint.setAntiAlias(true);
            textPaint.setColor(Color.WHITE);
            textPaint.setTextSize(75.0f);
            textPaint.setStrokeWidth(5.0f);
            textPaint.setStyle(Paint.Style.STROKE);
            textPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);
            c.drawText(_imagesSource.get(_current).getText(), c.getWidth() * 0.25f + _deltaX, getHeight() * 0.9f + _deltaY, textPaint);



        }


    }

    public void changeDelta(int dX, int dY){

        _deltaX = dX;
        _deltaY = dY;
    }

    public void next(boolean swipedRight) {

        if(swipedRight){

            Debug.Log("Right");

            _resources.getDrawable(_imagesSource.get(_current).getImageUri()).setAlpha(255);
            LikedImagesActivity.likedImages.add(_imagesSource.get(_current));

        }
        else{

            Debug.Log("Left");
        }

        try{

            if (_current < _imagesSource.size() - 1){

                _current++;
            }
            else {

                _resources.getDrawable(_imagesSource.get(_current).getImageUri()).setAlpha(255);
                MainActivity.Instance.toLikedList();
            }


        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }




    }



}
