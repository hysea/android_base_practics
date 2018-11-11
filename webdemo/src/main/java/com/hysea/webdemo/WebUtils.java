package com.hysea.webdemo;

import android.content.Context;
import android.content.res.AssetManager;
import android.webkit.WebView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class WebUtils {
    /**
     * 获取html页面
     *
     * @param bodyContent body内容，一些富文本信息
     */
    public static String getHtmlContent(String bodyContent) {
        return "<!DOCTYPE html\n><html>" +
                "<head>" + getMeta() + getCssLink() + /*头部信息*/
                "</head>" +
                "<body>" + bodyContent + "</body>" +
                getJsLink() + /*在页面渲染完成后加载js*/
                "</html>";
    }

    /**
     * 加载页面
     */
    public static void loadDataWithBaseURL(WebView webView, String bodyContent) {
        webView.loadDataWithBaseURL(null, getHtmlContent(bodyContent), "text/html", "utf-8", null);
    }


    /**
     * 移动端屏幕适配
     */
    public static String getMeta() {
        return "<meta name=\"viewport\"content=\"width=device-width, initial-scale=1, shrink-to-fit=no, user-scalable=no\">";
    }

    /**
     * 获取本地css引入
     */
    public static String getCssLink() {
        return "<link rel=\"stylesheet\" href=\"file:///android_asset/css/local.css\" type=\"text/css\">";
    }

    /**
     * 获取本地js引入
     */
    public static String getJsLink() {
        return "<script type=\"text/javascript\" src=\"file:///android_asset/js/local.js\"></script>";
    }

    public static String getJsContent(Context context) {
        AssetManager assets = context.getResources().getAssets();
        try {
            InputStream is = assets.open("local.js");
            String str = readStreamToString(is);
            is.close();
            return str;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String readStreamToString(InputStream inputStream) throws IOException {
        //创建字节数组输出流 ，用来输出读取到的内容
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //创建读取缓存,大小为1024
        byte[] buffer = new byte[1024];
        //每次读取长度
        int len = 0;
        //开始读取输入流中的文件
        while ((len = inputStream.read(buffer)) != -1) { //当等于-1说明没有数据可以读取了
            byteArrayOutputStream.write(buffer, 0, len); // 把读取的内容写入到输出流中
        }
        //把读取到的字节数组转换为字符串
        String result = byteArrayOutputStream.toString();

        //关闭输入流和输出流
        inputStream.close();
        byteArrayOutputStream.close();
        //返回字符串结果
        return result;
    }
}
