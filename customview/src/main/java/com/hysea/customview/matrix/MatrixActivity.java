package com.hysea.customview.matrix;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hysea.customview.R;

/**
 * 矩形变换
 *
 * 注意事项
 * 1.对于一个从BitmapFactory.decodeXxx()方法加载的Bitmap对象而言，它是一个只读的，
 * 无法对其进行处理，必须使用Bitmap.createBitmap()方法重新创建一个Bitmap对象的拷贝，
 * 才可以对拷贝的Bitmap进行处理。
 *
 * 2.因为图像的变换是针对每一个像素点的，所以有些变换可能发生像素点的丢失，
 * 这里需要使用Paint.setAntiAlias(boolean)设置来消除锯齿，这样图片变换后的效果会好很多
 *
 * 3.在重新创建一个Bitmap对象的拷贝的时候，需要注意它的宽高，如果设置不妥，很可能变换后的像素点已经移动到"图片之外"去了
 */
public class MatrixActivity extends AppCompatActivity {
    private Bitmap mOriginBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        mOriginBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
    }


    public void onScale(View view) {

    }

    private void bitmapScale(float x, float y) {
//        Bitmap bitmap = Bitmap.createBitmap((int) (mOriginBitmap.getWidth() * x),
//                (int) (mOriginBitmap.getHeight() * y),
//                mOriginBitmap.getConfig());
//
//        Canvas canvas = new Canvas(bitmap);
//        Matrix matrix = new Matrix();
//        matrix.setScale(x, y);
//        canvas.drawBitmap();
    }
}
