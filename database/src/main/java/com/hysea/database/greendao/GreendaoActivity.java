package com.hysea.database.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.hysea.database.R;

import java.util.List;

public class GreendaoActivity extends AppCompatActivity {
    private final static String TAG = GreendaoActivity.class.getSimpleName();
    private EditText etUsername;
    private EditText etAge;
    private EditText etUid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_greendao);

        etUsername = findViewById(R.id.username);
        etAge = findViewById(R.id.age);
        etUid = findViewById(R.id.uid);
    }

    public void insert(View view) {
        App.getDaoSession().getUserDao().insertOrReplace(getUser());
    }

    private User getUser() {
        int age = 0;
        try {
            age = Integer.parseInt(etAge.getText().toString());
        } catch (NumberFormatException ex) {
            age = 0;
        }
        return new User(etUsername.getText().toString(), age, etUid.getText().toString());
    }

    public void delete(View view) {
        App.getDaoSession().getUserDao().deleteAll();
    }

    public void update(View view) {

    }

    public void query(View view) {
        List<User> list = App.getDaoSession().getUserDao().queryBuilder().build().list();
        if (list != null && list.size() > 0) {
            Log.i(TAG, "数据库有数据：" + list.size());
            for (User user : list) {
                Log.i(TAG, user.toString());
            }
        } else {
            Log.i(TAG, "数据库为空");
        }
    }
}
