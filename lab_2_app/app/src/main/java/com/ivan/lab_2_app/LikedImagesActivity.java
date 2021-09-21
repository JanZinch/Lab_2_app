package com.ivan.lab_2_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class LikedImagesActivity extends AppCompatActivity {

    private ItemTouchHelper _itemTouchHelper = null;

    private RecyclerView _recyclerView = null;
    private RecyclerView.LayoutManager _layoutManager = null;

    private LikedImagesAdapter _adapter = null;
    private ItemTouchHelper.Callback _callback = null;


    public static List<ImageItem> likedImages = new ArrayList<>();

    protected void Load(){

        _adapter = new LikedImagesAdapter(likedImages, getResources(), 1000);

        _recyclerView = findViewById(R.id.liked_recycler_list);

        //_recyclerView.Init(ImageListAdapter._images, getResources());

        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        _layoutManager = new LinearLayoutManager(this);

        _recyclerView.setLayoutManager(_layoutManager);

        //_callback = new ImageTouchHelperCallback(_recyclerView, _adapter);
        //_itemTouchHelper = new ItemTouchHelper(_callback);
        //_itemTouchHelper.attachToRecyclerView(_recyclerView);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_liked_images);
        //setContentView(new DrawView(this));

        try{



            Load();
        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }






    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        likedImages.clear();
    }
}