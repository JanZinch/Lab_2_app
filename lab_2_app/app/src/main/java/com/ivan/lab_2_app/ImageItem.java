package com.ivan.lab_2_app;

public class ImageItem {

    private String text;
    private int imageUri;

    public ImageItem(String text, int imageUri){

        this.text = text;
        this.imageUri = imageUri;
    }

    public String getText(){

        return text;
    }

    public int getImageUri(){

        return imageUri;
    }

}
