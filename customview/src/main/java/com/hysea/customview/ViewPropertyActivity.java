package com.hysea.customview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.hysea.customview.utils.ScreenUtils;

/**
 * View的基本属性
 */
public class ViewPropertyActivity extends AppCompatActivity {
    private final static String TAG = ViewPropertyActivity.class.getSimpleName();
    private RelativeLayout mParentLayout;
    private View mChildView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_property);

        mParentLayout = findViewById(R.id.parent_layout);
        mChildView = findViewById(R.id.child_view);

        Log.d(TAG, "screen width = " + ScreenUtils.getScreenWidth(this));
        Log.d(TAG, "screen height = " + ScreenUtils.getScreenHeight(this));
        Log.d(TAG, "screen status = " + ScreenUtils.getStatusBarHeight(this));

        mParentLayout.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "parent : left = " + mParentLayout.getLeft() + ", top = " + mParentLayout.getTop());
                int[] location = new int[2];
                mParentLayout.getLocationInWindow(location);
                Log.i(TAG, "parent : location[0] = " + location[0] + ", location[1] = " + location[1]);
            }
        });

        mChildView.post(new Runnable() {
            @Override
            public void run() {
                Log.i(TAG, "child : left = " + mChildView.getLeft() + ", top = " + mChildView.getTop());
                int[] location = new int[2];
                mChildView.getLocationInWindow(location);
                Log.i(TAG, "child : location[0] = " + location[0] + ", location[1] = " + location[1]);
                Log.i(TAG, "child : x = " + mChildView.getX() + ", y = " + mChildView.getY());
            }
        });
    }
}
