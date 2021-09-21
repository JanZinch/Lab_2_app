package com.ivan.lab_2_app;

import android.graphics.Canvas;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ImageTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ImageTouchHelperAdapter _adapter;

    private ImageList _recycler = null;
    private int _deltaX, _deltaY;


    public ImageTouchHelperCallback(ImageList recycler, ImageTouchHelperAdapter adapter){

        _adapter = adapter;
        _recycler = recycler;
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return true;
    }




    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        try{

            int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

            return makeMovementFlags(dragFlags, swipeFlags);
        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }

        return  0;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        _recycler.changeDelta((int)dX, (int)dY);
        _deltaX = (int)dX;
        _deltaY = (int)dY;

    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder source, RecyclerView.ViewHolder target) {

        _adapter.OnImageMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onMoved(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder,
                        int fromPos, @NonNull RecyclerView.ViewHolder target, int toPos, int x, int y) {

    }



    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        Debug.Log("SWIPE: " + ItemTouchHelper.END);
        _adapter.OnImageDismiss(viewHolder.getAdapterPosition());


        _recycler.next(direction == ItemTouchHelper.END);

    }



    /*@Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {

        Debug.Log("SWIPE: " + i);
        _adapter.OnImageDismiss(viewHolder.getAdapterPosition());


        _recycler.next(i == 32);

    }*/

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            ImageTouchHelperViewHolder itemViewHolder = (ImageTouchHelperViewHolder) viewHolder;
            itemViewHolder.OnImageSelected();
        }

        super.onSelectedChanged(viewHolder, actionState);
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);

        ImageTouchHelperViewHolder itemViewHolder = (ImageTouchHelperViewHolder) viewHolder;
        itemViewHolder.OnImageClear();

        _recycler.changeDelta(0,0);
    }




}
