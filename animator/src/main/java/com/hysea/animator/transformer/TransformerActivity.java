package com.hysea.animator.transformer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hysea.animator.R;

/**
 * Created by hysea on 2018/8/24.
 */
public class TransformerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transformer);
        ViewPager mViewPager = findViewById(R.id.viewpager);
        mViewPager.setOffscreenPageLimit(2);
        // 3D旋转效果
        mViewPager.setPageTransformer(true, new PageTransformer3D());
        mViewPager.setAdapter(new TransformerPagerAdapter(this));
    }


    public static class TransformerPagerAdapter extends PagerAdapter {
        private Context mContext;
        private static final int IMAGES[] = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};

        public TransformerPagerAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return IMAGES.length;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            ImageView imageView = new ImageView(mContext);
            imageView.setBackgroundResource(IMAGES[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }
}
