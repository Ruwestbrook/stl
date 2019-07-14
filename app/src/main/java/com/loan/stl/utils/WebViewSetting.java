package com.loan.stl.utils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.loan.stl.utils.JavaScriptInterface;

/**
 * Created by ggp on 2017/10/11.
 */

public class WebViewSetting {
    @SuppressWarnings("deprecation")
    @SuppressLint({"SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void setWebViewSetting(WebView webView, JavaScriptInterface javaScriptInterface) {

        WebSettings webSettings = webView.getSettings();
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setSavePassword(false);
        webView.setSoundEffectsEnabled(true);

        webView.addJavascriptInterface(javaScriptInterface,"JavaScriptInterface");
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        // 设置支持缩放
        webSettings.setBuiltInZoomControls(true);
        webView.setVerticalScrollbarOverlay(false);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        // 存在XSS漏洞
        webSettings.setJavaScriptEnabled(true);
        // 移除Android低版本XSS漏洞
        if (Build.VERSION.SDK_INT >= 11 && Build.VERSION.SDK_INT < 17) {
            try {
                webView.removeJavascriptInterface("searchBoxJavaBridge_");
                webView.removeJavascriptInterface("accessibility");
                webView.removeJavascriptInterface("accessibilityTraversal");
            } catch (Throwable tr) {
                tr.printStackTrace();
            }
        }
        webSettings.setDomStorageEnabled(true);
//        webSettings.setDefaultTextEncodingName("GBK");//设置字符编码  设置中文支持
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true); // 关键点
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); // 不加载缓存内容
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setDisplayZoomControls(false);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDatabaseEnabled(true);
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            webSettings.setAppCacheMaxSize(1024 * 1024 * 4);
            webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_COMPATIBILITY_MODE);
        }
//        webView.setBackgroundColor(Color.TRANSPARENT); // 设置背景色
//        webView.getBackground().setAlpha(0); // 设置填充透明度 范围：0-255
        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_UP:
                        if (!v.hasFocus()) {
                            v.requestFocus();
                        }
                        break;
                }
                return false;
            }
        });
    }
}
