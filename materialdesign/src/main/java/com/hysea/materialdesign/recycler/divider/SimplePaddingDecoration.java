package com.hysea.materialdesign.recycler.divider;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hysea.materialdesign.utils.Utils;

public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int dividerHeight;
    private Context context;
    private Paint mPaint;

    public SimplePaddingDecoration(Context context) {
        this.context = context;
        dividerHeight = Utils.dp2px(context, 2);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        int position = parent.getChildAdapterPosition(view);
//        if (position == 0) {
//            outRect.top = dividerHeight;
//        }
        outRect.bottom = dividerHeight; // 类似加了一个bottom padding
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int childCount = parent.getChildCount();

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + dividerHeight;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }
}
