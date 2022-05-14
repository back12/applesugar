package com.example.applesugar.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class RecyclerViewItemDecoration extends RecyclerView.ItemDecoration {
    //horizontal space
    private int var1 = 0;
    //vertical space when gridlayout
    // or
    //edge space when linearlayout
    private int var2 = 0;
    private int sPosition = 0;

    public RecyclerViewItemDecoration(Context context, int var1, int var2, int sPosition) {
        this.var1 = ScreenUtil.dip2px(context, var1);
        this.var2 = ScreenUtil.dip2px(context, var2);
        this.sPosition = sPosition;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        if (parent.getLayoutManager() instanceof GridLayoutManager) {
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            getGridItemOffsets(outRect, position, layoutManager, null);
        } else if (parent.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            StaggeredGridLayoutManager layoutManager = (StaggeredGridLayoutManager) parent.getLayoutManager();
            getGridItemOffsets(outRect, position, layoutManager, view);
        } else if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                if (position >= sPosition) {
                    outRect.bottom = var1 / 2;
                    outRect.top = var1 / 2;
                }
                if (position == sPosition) {
                    outRect.top = var1 + var2;
                    outRect.bottom = var1 / 2;
                } else if (position == state.getItemCount() - 1) {
                    outRect.bottom = var1 + var2;
                    outRect.top = var1 / 2;
                }
            } else if (layoutManager.getOrientation() == LinearLayoutManager.HORIZONTAL) {
                if (position >= sPosition) {
                    outRect.left = var1 / 2;
                    outRect.right = var1 / 2;
                }
                if (position == sPosition) {
                    outRect.left = var1 + var2;
                    outRect.right = var1 / 2;
                } else if (position == state.getItemCount() - 1) {
                    outRect.left = var1 / 2;
                    outRect.right = var1 + var2;
                }
            }
        }
    }

    private void getGridItemOffsets(Rect outRect, int position, RecyclerView.LayoutManager layoutManager, View view) {
        int spanCount;
        int column;
        if (layoutManager instanceof GridLayoutManager) {
            spanCount = ((GridLayoutManager) layoutManager).getSpanCount() / ((GridLayoutManager) layoutManager).getSpanSizeLookup().getSpanSize(position);
            column = (position - sPosition) % spanCount;

        } else {
            spanCount = ((StaggeredGridLayoutManager) layoutManager).getSpanCount();
            StaggeredGridLayoutManager.LayoutParams lp = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            column = lp.getSpanIndex();
        }
        if (position >= sPosition) {
            outRect.left = var1 * (spanCount - column) / spanCount;
            outRect.right = var1 * (column + 1) / spanCount;
            outRect.bottom = var2;

            if (position < spanCount + sPosition) {
                outRect.top = var2;
            }
        }

    }

}
