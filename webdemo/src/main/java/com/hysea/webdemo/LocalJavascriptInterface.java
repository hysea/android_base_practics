package com.hysea.webdemo;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * js交互接口
 */
public class LocalJavascriptInterface {

    private static final String TAG = LocalJavascriptInterface.class.getSimpleName();

    /**
     * Js调用方法变量，必须与js代码里的变量保持一致
     */
    public static final String JS_VARIABLE = "AndroidNative";

    private Context mContext;

    public LocalJavascriptInterface(Context context) {
        this.mContext = context;
    }

    /**
     * 点击展示大图
     *
     * @param scr 图片Url
     * @param pos 图片所在位置
     */
    @JavascriptInterface
    public void openImage(String scr, int pos) {
        Log.i(TAG, "pos : " + pos + ", src = " + scr);
    }

    @JavascriptInterface
    public void getHtmlContent(String html) {
        //取出HTML中P标签的文本内容,利用正则表达式匹配.
        Pattern pattern = Pattern.compile("<p.*?>(.*?)</p>");
        Matcher matcher = pattern.matcher(html);
        StringBuilder builder = new StringBuilder();
        while (matcher.find()) {
            builder.append(matcher.group(1));
        }
        Log.i(TAG, builder.toString());
    }
}
