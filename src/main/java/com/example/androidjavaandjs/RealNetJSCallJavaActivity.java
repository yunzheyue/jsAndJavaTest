package com.example.androidjavaandjs;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/1/2.
 */
public class RealNetJSCallJavaActivity extends Activity {
    /**
     * 服务器地址，用浏览器也可以打开
     */
//    private static final String url = "http://api.lol.zhangyoubao.com/mobiles/item/65411?v_=400800&size=middle&t=1462666251";
    private WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realnet_jscalljava);
        /**
         * WebView的对象
         */
        wv = (WebView) findViewById(R.id.webview);
        /**
         * 如果访问的页面中有Javascript，则webview必须设置支持Javascript。
         */
        WebSettings setting = wv.getSettings();
        setting.setJavaScriptEnabled(true);
        /**
         * 设置支持显示缩放的按钮
         */
        setting.setBuiltInZoomControls(true);
        wv.loadUrl("http://192.168.0.12:8080/RealNetJSCallJavaActivity.htm");
//        wv.loadUrl(url);

        /**
         * js调用java
         * "android"是Key，js调用new Video()的方法时 ，必须用此Key，key名字自己去
         */
        wv.addJavascriptInterface(new Video(), "android");

    }

    private class Video {
        @JavascriptInterface
        public void playVideo(int itemid, String videourl, String itemtitle) {
            // 把系统所有的播放调起来
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.parse(videourl), "video/*");
            startActivity(intent);
        }

        @JavascriptInterface
        public void offlineVideo(int itemid, String videourl, String itemtitle, String itemdesc, String itempic) {
            Toast.makeText(RealNetJSCallJavaActivity.this, "下载视频：" + videourl, Toast.LENGTH_SHORT).show();
        }
    }
}
