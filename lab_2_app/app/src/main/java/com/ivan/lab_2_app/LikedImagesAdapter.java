package com.ivan.lab_2_app;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LikedImagesAdapter extends RecyclerView.Adapter<LikedImagesAdapter.LikedImagesViewHolder> {

    List<ImageItem> _images = null;
    Resources res;
    int screenWidth;

    public LikedImagesAdapter(List<ImageItem> images, Resources res, int screenWidth) {
        _images = images;
        this.res = res;
        this.screenWidth = screenWidth;
    }

    @NonNull
    @Override
    public LikedImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new LikedImagesViewHolder(itemView);
    }


    @Override
        public void onBindViewHolder(@NonNull LikedImagesViewHolder holder, int position) {
            holder.bind(res.getDrawable(_images.get(position).getImageUri()), screenWidth);
        }

    @Override
    public int getItemCount() {
        return _images.size();
    }

    public static class LikedImagesViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public LikedImagesViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.item_image);
        }

        void bind(Drawable drawable, int screenWidth) {
            imageView.setImageDrawable(drawable);
            //int insHeight = drawable.getIntrinsicHeight();
            //int insWidth = drawable.getIntrinsicWidth();
            //float coefW = insWidth * 1f / screenWidth;
            //itemView.getLayoutParams().height = (int)(insHeight/coefW);


            itemView.setPadding(300, 50, 0, 0);


        }

    }


}