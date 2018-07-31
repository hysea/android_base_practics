package com.hysea.inputmethod.common;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hysea.inputmethod.R;

/**
 * 普通Activity
 * <p>
 * 在进入界面时隐藏键盘
 * 1.在AndroidManifest.xml中对相应的Activity添加一下代码：
 * android:windowSoftInputMode="stateHidden"
 * 2.在java代码中调用：
 * getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
 *
 *
 * 当界面内容较多，点击弹起键盘时，默认情况下，键盘会把界面中的内容
 */
public class CommonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
    }
}
