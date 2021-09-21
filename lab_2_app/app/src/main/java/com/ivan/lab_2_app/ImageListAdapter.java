package com.ivan.lab_2_app;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import androidx.annotation.RequiresApi;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageViewHolder> implements ImageTouchHelperAdapter {


    public final OnStartDragListener startDragListener;

    private static final List<ImageItem> _imagesSource = new ArrayList<ImageItem>();

    public static ImageItem[] _images = new ImageItem[]{

            new ImageItem("first",  R.drawable.actin),
            new ImageItem("second", R.drawable.cur),
            new ImageItem("third", R.drawable.money),
            new ImageItem("fourth", R.drawable.platon),
            new ImageItem("fifth", R.drawable.samuro),
            new ImageItem("fifth", R.drawable.ship),
            new ImageItem("fifth", R.drawable.tree),
            new ImageItem("fifth", R.drawable.gorila),
            new ImageItem("fifth", R.drawable.waterfall),
            new ImageItem("fifth", R.drawable.rino)

    };

    private void LoadList(){

        _imagesSource.addAll(Arrays.asList(_images));
    }


    public ImageListAdapter(OnStartDragListener startDragListener){


        LoadList();
        this.startDragListener = startDragListener;
    }



    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        ImageViewHolder itemViewHolder = new ImageViewHolder(view);
        return itemViewHolder;
    }


    @Override
    public void onBindViewHolder(final ImageViewHolder holder, int position) {

        //holder.textView.setText(_imagesSource.get(position).getText());


        //holder.imageView.setImageResource(_imagesSource.get(position).getImageUri());

        holder.imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    startDragListener.onStartDrag(holder);
                }
                return false;
            }

        });
    }

    @Override
    public void OnImageDismiss(int position) {
        _imagesSource.remove(position);
        notifyItemRemoved(position);
    }





    @Override
    public void OnImageMove(int fromPosition, int toPosition) {

        ImageItem prev = _imagesSource.remove(fromPosition);
        _imagesSource.add(toPosition > fromPosition ? toPosition - 1 : toPosition, prev);
        notifyItemMoved(fromPosition, toPosition);


    }

    @Override
    public int getItemCount() {
        return _imagesSource.size();
    }


    public static class ImageViewHolder extends RecyclerView.ViewHolder implements ImageTouchHelperViewHolder {

        public final TextView textView;
        public final ImageView imageView;

        public ImageViewHolder(View itemView) {
            super(itemView);
            //textView = (TextView) itemView;

            textView = (TextView) itemView.findViewById(R.id.item_text);
            imageView = (ImageView) itemView.findViewById(R.id.item_image);
        }

        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void OnImageSelected() {

            //itemView.setBackgroundColor(Color.LTGRAY);
            itemView.setOutlineSpotShadowColor(Color.GREEN);

        }

        @RequiresApi(api = Build.VERSION_CODES.P)
        @Override
        public void OnImageClear() {

            //itemView.setBackgroundColor(0);
            itemView.setOutlineSpotShadowColor(Color.CYAN);
        }
    }

}

