package com.hysea.materialdesign.recycler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hysea.materialdesign.R;
import com.hysea.materialdesign.recycler.divider.SimplePaddingDecoration;

public class RecyclerActivity extends AppCompatActivity {
    private RecyclerView mRvList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRvList = findViewById(R.id.rv_list);

        mRvList.setLayoutManager(new LinearLayoutManager(this));
        mRvList.addItemDecoration(new SimplePaddingDecoration(this));
        mRvList.setAdapter(new SimpleAdapter(this, DataProvider.providerSimpleData()));
    }
}
