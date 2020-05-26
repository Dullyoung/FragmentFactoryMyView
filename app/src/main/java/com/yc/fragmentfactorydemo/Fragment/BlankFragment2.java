package com.yc.fragmentfactorydemo.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

import com.yc.fragmentfactorydemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment2 extends Fragment {


    public BlankFragment2() {
        // Required empty public constructor
    }

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_blank_fragment2, container, false);
        init();
        return view;

    }

    WebView webView;

    void init() {
        webView = view.findViewById(R.id.webView);

        webView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);// 支持缩放
        webSettings.setLoadsImagesAutomatically(true); // 设置可以自动加载图片
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT); // 关闭webview中缓存
        webSettings.setDomStorageEnabled(true); // 设置DOM缓存
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        WebViewClient webViewClient = new WebViewClient();
        webView.setWebViewClient(webViewClient);
        EditText editText=view.findViewById(R.id.ed);
        view.findViewById(R.id.load).setOnClickListener(v -> webView.loadUrl("http://" + editText.getText().toString()));
    }



}
