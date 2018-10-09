package com.hysea.network;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.hysea.network.okhttp.OkHttpActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toOkhttp(View view) {
        Intent intent = new Intent(this, OkHttpActivity.class);
        startActivity(intent);
    }
}
