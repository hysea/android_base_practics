package com.hysea.animator.propertyAnimator;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hysea.animator.R;
import com.hysea.animator.utils.ScreenUtilsKt;

/**
 * Created by hysea on 2018/8/24.
 */
public class PropertyAnimatorActivity extends AppCompatActivity {
    private static final String TAG = PropertyAnimatorActivity.class.getSimpleName();
    private Context mContext;
    private Button mBtnSubmit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_animator);
        mContext = this;
        mBtnSubmit = findViewById(R.id.btn_submit);
    }

    public void onSubmit(View v) {
        startAnimator();
    }


    private void startAnimator() {
        int measuredWidth = mBtnSubmit.getMeasuredWidth();
        int measuredHeight = mBtnSubmit.getMeasuredHeight();

        // 位移动画
        ValueAnimator translateAnimator = ValueAnimator.ofInt(measuredWidth, measuredHeight);
        translateAnimator.setDuration(2000);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mBtnSubmit.getLayoutParams();
                layoutParams.width = value;
                mBtnSubmit.setLayoutParams(layoutParams);
            }
        });

        // 圆形动画
        ValueAnimator circleAnimator = ValueAnimator.ofInt(0, measuredWidth / 2);
        circleAnimator.setDuration(1000);
        circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int) animation.getAnimatedValue();
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(value);
                drawable.setColor(ContextCompat.getColor(mContext, android.R.color.darker_gray));
                mBtnSubmit.setBackground(drawable);
            }
        });

        PointF controlPoint1 = new PointF(-ScreenUtilsKt.getScreenWidth(mContext) / 6, ScreenUtilsKt.getScreenHeight(mContext) / 6);
        PointF controlPoint2 = new PointF(-ScreenUtilsKt.getScreenWidth(mContext) / 5, ScreenUtilsKt.getScreenHeight(mContext) / 5);

        PointF startPoint = new PointF(mBtnSubmit.getX(), mBtnSubmit.getY());
        PointF endPoint = new PointF(ScreenUtilsKt.getScreenWidth(mContext) / 2, ScreenUtilsKt.getScreenHeight(mContext) - measuredHeight * 2);

        // 贝塞尔曲线动画
        BezierEvaluator evaluator = new BezierEvaluator(controlPoint1, controlPoint2);
        ValueAnimator bezierAnimator = ValueAnimator.ofObject(evaluator, startPoint,endPoint);
        bezierAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                mBtnSubmit.setX(pointF.x);
                mBtnSubmit.setY(pointF.y);
            }
        });
        bezierAnimator.setDuration(500);


        AnimatorSet set = new AnimatorSet();
        set.playSequentially(translateAnimator, circleAnimator, bezierAnimator);
        set.start();
    }
}
