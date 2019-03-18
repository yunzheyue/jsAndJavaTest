package com.example.androidjavaandjs;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Java调用html里面的javascript代码
 * <p/>
 * 这个案例其实是js->Java->js
 */
public class JavaCallJSActivity2 extends Activity {

    private WebView mWebView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showWebView();
    }

    /**
     * java和h5互调的博客
     * <p>
     * http://blog.csdn.net/u013424496/article/details/51991541
     */

    private void showWebView() {
        // webView与js交互代码
        try {
            mWebView = new WebView(this);
            setContentView(mWebView);

            mWebView.requestFocus();
            //支持在html中弹出对话框
            mWebView.setWebChromeClient(new MyWebChromeClient());
            mWebView.setOnKeyListener(new MyOnKeyListener());
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDefaultTextEncodingName("utf-8");
            // 添加一个对象, 让JS可以访问该对象的方法, 该对象中可以调用JS中的方法
            //注意Java调用JS的时候addJavascriptInterface是不必须的
            /**
             * addJavascriptInterface方法
             此方法有两个参数，第一个参数就是提供一个方法，方法中提供一个内部类，
             类里面提供我们要提供给javascript访问的方法；

             第二个参数暴露给js的interfaceName
             在js中调用模式为window.interfaceName.方法名()
             或者是javascript: interfaceName.方法名() ;
             例如下面的android就是interfaceName
             */
            mWebView.addJavascriptInterface(new JsInterface(), "android");
//			mWebView.loadUrl("http://10.0.2.2:8080/JavaCallJS2.html");
            mWebView.loadUrl("file:///android_asset/JavaCallJS2.html");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class MyOnKeyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
            return false;

        }

    }

    class MyWebChromeClient extends WebChromeClient {

        @Override
        public void onProgressChanged(WebView view, int progress) {
            JavaCallJSActivity2.this.setTitle("Loading...");
            JavaCallJSActivity2.this.setProgress(progress);

            if (progress >= 80) {
                JavaCallJSActivity2.this.setTitle("JsAndroid Test");
            }
        }

    }


    //这是提供给JavaScriptInterface的对象，
    //在这里对于每一个提供给JS的方法都要使用@JavascriptInterface注解标注，否则对于api>17就会出现警告
    private class JsInterface {
        @JavascriptInterface
        public void JavacallHtml() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mWebView.loadUrl("javascript: showFromHtml()");
                    Toast.makeText(JavaCallJSActivity2.this, "clickBtn",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
//        从js传递过来数据
        @JavascriptInterface
        public void JavacallHtml2(final String name) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.e("TAG", "name==" + name);
//                    注意在这里方法参数传递时的拼串操作
                    mWebView.loadUrl("javascript: showFromHtml2('我是" + name + "')");
                    Toast.makeText(JavaCallJSActivity2.this, "clickBtn2",
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}