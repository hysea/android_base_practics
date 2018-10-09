package com.hysea.media.surfaceview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * SurfaceView基本模板s
 *
 * @author hysea
 */
public class TemplateSurfaceView extends SurfaceView implements SurfaceHolder.Callback, Runnable {

    private SurfaceHolder mHolder;
    /**
     * 用于绘图的Canvas
     */
    private Canvas mCanvas;
    /**
     * 子线程标志位
     */
    private boolean mIsDrawing;

    public TemplateSurfaceView(Context context) {
        this(context, null);
    }

    public TemplateSurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TemplateSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        mHolder = getHolder();
        mHolder.addCallback(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setKeepScreenOn(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            draw();
        }
    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            // draw something
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (mCanvas != null) {
                // 将画布内容进行提交
                // 注意：应该将此方法放到finally代码块中，保证每次都能讲内容提交
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }
}
