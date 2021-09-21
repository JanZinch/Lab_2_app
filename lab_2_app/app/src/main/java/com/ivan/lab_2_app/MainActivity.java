package com.ivan.lab_2_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity implements OnStartDragListener {

    public static MainActivity Instance = null;

    private ItemTouchHelper _itemTouchHelper = null;

    private ImageList _recyclerView = null;
    private RecyclerView.LayoutManager _layoutManager = null;

    private ImageListAdapter _adapter = null;
    private ItemTouchHelper.Callback _callback = null;

    private RecyclerView.ItemDecoration _itemDecoration = null;

    protected void Load(){

        _adapter = new ImageListAdapter(this);

        _recyclerView = findViewById(R.id.recycler_list);
        //_recyclerView = new RecyclerView(this, )

        _recyclerView.Init(ImageListAdapter._images, getResources());

        _recyclerView.setHasFixedSize(true);
        _recyclerView.setAdapter(_adapter);

        _layoutManager = new LinearLayoutManager(this){

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        _recyclerView.setLayoutManager(_layoutManager);

        _callback = new ImageTouchHelperCallback(_recyclerView, _adapter);
        _itemTouchHelper = new ItemTouchHelper(_callback);
        _itemTouchHelper.attachToRecyclerView(_recyclerView);

        _itemDecoration = new ImageListDecoration();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        //setContentView(new DrawView(this));

        Instance = this;

        try{



            Load();
        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }






    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {

        _itemTouchHelper.startDrag(viewHolder);
    }

    public void toLikedList(){

        try{

            Debug.Log("INP");

            startActivity(new Intent(MainActivity.this, LikedImagesActivity.class));
            //Debug.Log("Success!");
        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }



    }



    class DrawView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;

        public DrawView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            drawThread = new DrawThread(getHolder(), this.getResources(), _recyclerView);
            drawThread.setRunning(true);
            drawThread.start();
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            boolean retry = true;
            drawThread.setRunning(false);
            while (retry) {
                try {
                    drawThread.join();
                    retry = false;
                } catch (InterruptedException e) {
                }
            }
        }

        class DrawThread extends Thread {

            private boolean running = false;
            private SurfaceHolder surfaceHolder;
            private Resources resources = null;
            private RecyclerView recyclerListView = null;


            public DrawThread(SurfaceHolder surfaceHolder, Resources resources, RecyclerView recyclerView) {
                this.surfaceHolder = surfaceHolder;
                this.resources = resources;
                this.recyclerListView = recyclerView;
            }

            public void setRunning(boolean running) {
                this.running = running;
            }


            private void draw(Canvas canvas){

                Paint paint = new Paint();
                paint.setColor(Color.CYAN);
                paint.setStrokeWidth(5);
                paint.setStyle(Paint.Style.FILL);
                paint.setAntiAlias(true);

                canvas.drawCircle(500, 500, 200, paint);


                paint.setColor(Color.YELLOW);
                canvas.drawRect(100, 50, 200, 300, paint);


                Paint textPaint = new Paint();
                textPaint.setAntiAlias(true);
                textPaint.setColor(Color.WHITE);
                textPaint.setTextSize(75.0f);
                textPaint.setStrokeWidth(5.0f);
                textPaint.setStyle(Paint.Style.STROKE);
                textPaint.setShadowLayer(5.0f, 10.0f, 10.0f, Color.BLACK);
                canvas.drawText("This is a text...", canvas.getWidth() * 0.25f, getHeight() * 0.5f, textPaint);

                textPaint.setShadowLayer(0.0f, 0.0f, 0.0f, Color.BLACK);

                try{



                    Bitmap image = BitmapFactory.decodeResource(resources, R.drawable.actin);
                    //canvas.drawBitmap(image, canvas.getWidth() * 0.25f, getHeight() * 0.5f, textPaint);
                    Rect srcrect = new Rect(0, 0, image.getWidth(), image.getHeight());
                    Rect outrect = new Rect(0,0, 800, 600);
                    canvas.drawBitmap(image, srcrect, outrect, textPaint);

                    recyclerListView = (RecyclerView) findViewById(R.id.recycler_list);
                    recyclerListView.draw(canvas);

                }
                catch (Exception ex){


                    Debug.Log(ex.getMessage());
                }

            }


            @Override
            public void run() {
                Canvas canvas;
                while (running) {
                    canvas = null;
                    try {
                        canvas = surfaceHolder.lockCanvas(null);
                        if (canvas == null) continue;
                        canvas.drawColor(Color.GREEN);

                        draw(canvas);

                    }
                    finally {
                        if (canvas != null) {
                            surfaceHolder.unlockCanvasAndPost(canvas);
                        }
                    }
                }
            }
        }

    }
}