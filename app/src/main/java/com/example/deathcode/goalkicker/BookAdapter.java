package com.example.deathcode.goalkicker;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by deathcode on 15/01/18.
 */

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.CustomViewHolder> {

    List<BookModel> bookModels;
    Context context;

    public BookAdapter(List<BookModel> bookModels, Context context) {
        this.bookModels = bookModels;
        this.context = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.book_image_row, parent, false));
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        BookModel book = bookModels.get(position);
        Picasso.with(context).load(book.getImageLink()).into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return bookModels.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{
        ImageView bookImage;
        public CustomViewHolder(View itemView) {
            super(itemView);
            bookImage = itemView.findViewById(R.id.bookimage);
        }
    }

    public interface OnClick{
        void onTap(View view, int position);
    }

    public static class RecylerTapListener implements RecyclerView.OnItemTouchListener{


        GestureDetector gestureDetector;
        BookAdapter.OnClick onClick;

        public RecylerTapListener(Context context, final RecyclerView recyclerView, final BookAdapter.OnClick onClick){
            this.onClick = onClick;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){

                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && onClick != null) {
                        onClick.onTap(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && onClick != null && gestureDetector.onTouchEvent(e)) {
                onClick.onTap(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }


}
