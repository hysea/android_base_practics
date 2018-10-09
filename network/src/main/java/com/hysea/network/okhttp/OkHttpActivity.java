package com.hysea.network.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.hysea.network.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class OkHttpActivity extends AppCompatActivity {
    private static final String TAG = OkHttpActivity.class.getSimpleName();

    private OkHttpClient mOkHttpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);
        mOkHttpClient = new OkHttpClient();
    }

    public void doGet(View view) {
        Request request = new Request.Builder()
                .url("http://www.wanandroid.com/article/list/0/json")
                .build();

        Call call = mOkHttpClient.newCall(request);
        // call.execute(); // 同步请求，这种方式会阻塞调用线程，所以应该单独开启一个线程执行，否则会引起ANR异常
        // 异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.i(TAG, Thread.currentThread().getName()); // new thread
                // 不能才此方法中更新UI
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.i(TAG, Thread.currentThread().getName()); // main thread
                    }
                });
                Log.i(TAG, response.body().string());
            }
        });
    }

    public void doPost(View view) {
        // 表单提交
        RequestBody requestBody = new FormBody.Builder()
                .add("username", "hysealive@163.com")
                .add("password", "hysealive")
                .build();

        Request request = new Request.Builder()
                .url("http://www.wanandroid.com/user/login")
                .post(requestBody)
                .build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d(TAG, response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.d(TAG, headers.name(i) + ":" + headers.value(i));
                }
                Log.i(TAG, "response : " + response.body().string());
            }
        });
    }

    private RequestBody requestBody = new RequestBody() {
        @Nullable
        @Override
        public MediaType contentType() {
            // 获取请求体的类型
            return MediaType.parse("text/x-markdown; charset=utf-8");
        }

        @Override
        public long contentLength() throws IOException {
            // 获取请求体的数据长度
            return super.contentLength();
        }

        @Override
        public void writeTo(BufferedSink sink) throws IOException {
            // 将请求正文写到指定的输出流中
            sink.writeUtf8("request body");
        }
    };
}
