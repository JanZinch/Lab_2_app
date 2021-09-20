package com.ivan.lab_2_app;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class ImageTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ImageTouchHelperAdapter _adapter;



    public ImageTouchHelperCallback(ImageTouchHelperAdapter adapter){

        _adapter = adapter;
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

            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;

            return makeMovementFlags(dragFlags, swipeFlags);
        }
        catch (Exception ex){

            Debug.Log(ex.getMessage());
        }

        return  0;


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
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        _adapter.OnImageDismiss(viewHolder.getAdapterPosition());
    }

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
    }




}
