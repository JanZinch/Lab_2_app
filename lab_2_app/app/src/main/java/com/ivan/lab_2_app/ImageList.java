package com.ivan.lab_2_app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
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




        }


    }

    public void changeDelta(int dX, int dY){

        _deltaX = dX;
        _deltaY = dY;
    }

    public void next(boolean swipedRight) {

        if(swipedRight){

            Debug.Log("Right");
        }
        else{

            Debug.Log("Left");
        }

        _current++;
    }



}
