package com.example.deathcode.goalkicker;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by deathcode on 15/01/18.
 */

public class ItemSpaceDecoration extends RecyclerView.ItemDecoration {

    int halfSpace;

    public ItemSpaceDecoration(int spacing) {
        halfSpace = spacing/2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        if(parent.getPaddingLeft()!=halfSpace){
            parent.setPadding(halfSpace, halfSpace, halfSpace, halfSpace);
            parent.setClipToPadding(false);
        }

        outRect.top = halfSpace;
        outRect.right = halfSpace;
        outRect.left = halfSpace;
        outRect.bottom = halfSpace;

    }
}
