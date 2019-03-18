package com.example.androidjavaandjs;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Java调用html里面的javascript代码
 *
 * @author 杨光福
 */
public class JavaCallJSActivity1 extends Activity {

    private WebView webview = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_call_javascript);
        webview = (WebView) findViewById(R.id.webview);
        // 启用javascript支持
        webview.getSettings().setJavaScriptEnabled(true);
        // 从assets目录下面的加载html
        //JavaCallJSActivity.html 该文件大小写没问题
        webview.loadUrl("file:///android_asset/JavaCallJS1.html");
//		webview.loadUrl("http://10.0.2.2:8080/JavaCallJS1.html");

        //支持从html中弹出对话框
        webview.setWebChromeClient(new WebChromeClient());


        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(btnClickListener);

        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(btnClickListener);

        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(btnClickListener);


    }

    OnClickListener btnClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1://Java调用吴参数的js的函数
                    // 无参数调用
                    webview.loadUrl("javascript:javaCallJs()");
                    break;
                case R.id.button2:
                    // 调用有参数的js的函数
                    // 传递参数调用
                    webview.loadUrl("javascript:javaCallJswithargs(" + "'我是阿福'" + ")");
                    break;
                case R.id.button3:
                    //调用 HTML 中的javaScript 函数 弹出对话框、
                    //webview.setWebChromeClient(new WebChromeClient()); 这行代码不能少
                    webview.loadUrl("javascript:showMsg()");
                    break;
                default:
                    break;
            }

        }
    };

}