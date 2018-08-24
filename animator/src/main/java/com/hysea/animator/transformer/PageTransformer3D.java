package com.hysea.animator.transformer;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 3D旋转动画
 * Created by hysea on 2018/8/23.
 */
public class PageTransformer3D implements ViewPager.PageTransformer {
    private static final float DEFAULT_ROTATION = 90.0f;

    /**
     * 旋转角度，值越大，3d效果越明显
     */
    private float mRotation;

    public PageTransformer3D() {
        this(DEFAULT_ROTATION);
    }

    public PageTransformer3D(float rotation) {
        mRotation = rotation;
    }

    @Override
    public void transformPage(@NonNull View view, float position) {
        view.setPivotY(view.getHeight() / 2);
        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setPivotX(0);
            view.setRotationY(90);
        } else if (position <= 0) { // [-1,0]
            view.setPivotX(view.getWidth());
            view.setRotationY(position * mRotation);
        } else if (position <= 1) { // (0,1]
            view.setPivotX(0);
            view.setRotationY(position * mRotation);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setPivotX(0);
            view.setRotationY(90);
        }
    }
}
