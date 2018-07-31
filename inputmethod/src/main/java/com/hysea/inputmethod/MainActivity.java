package com.hysea.inputmethod;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hysea.inputmethod.common.CommonActivity;
import com.hysea.inputmethod.immersion.ImmersionActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onCommon(View view) {
        Intent intent = new Intent(this, CommonActivity.class);
        startActivity(intent);
    }

    public void onImmersion(View view) {
        Intent intent = new Intent(this, ImmersionActivity.class);
        startActivity(intent);
    }
}
