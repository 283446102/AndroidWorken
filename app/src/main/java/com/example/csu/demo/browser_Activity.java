package com.example.csu.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class browser_Activity extends AppCompatActivity implements View.OnClickListener {
    private ProgressDialog dialog;
    private WebView webView;
    private String url ="http://www.baidu.com";
    private ProgressBar browser_progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_browser);
        init();
    }

    private void init() {
        webView = findViewById(R.id.webView);
        browser_progressBar = findViewById(R.id.browser_progressBar);
        ImageView forward_image_bt = findViewById(R.id.forward_image_bt);
        ImageView goback_image_bt = findViewById(R.id.goback_image_bt);
        ImageView home_image_bt = findViewById(R.id.home_image_bt);
        ImageView menu_radio_bt = findViewById(R.id.menu_radio_bt);
        ImageView window_number_radio_bt = findViewById(R.id.window_number_radio_bt);


       /* Drawable drawable_goback = getResources().getDrawable(R.drawable.goback_iocn);
        drawable_goback.setBounds(0,0,50,50);
        goback_image_bt.setImageDrawable(drawable_goback);*/



        forward_image_bt.setOnClickListener(this);
        goback_image_bt.setOnClickListener(this);
        home_image_bt.setOnClickListener(this);

        browser_progressBar.setMax(100);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress ==100){
                    //加载完毕
                    browser_progressBar.setProgress(0);
//                    closeDialog();
                }else {
                    //正在加载
                    browser_progressBar.setProgress(newProgress);
//                    openDialog(newProgress);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                setTitle(title);
            }
        });

        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url==null){
                    return false;
                }
                try {
                    if (!url.startsWith("http://")&&!url.startsWith("https://")){
                        Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                        return true;
                    }
                }catch (Exception e){
                    return true;
                }
                view.loadUrl(url);
                return true;
            }
        });

    }

/*    private void openDialog(int newProgress) {
        if (dialog == null){
            dialog = new ProgressDialog(browser_Activity.this);
            dialog.setTitle("正在加载");
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setProgress(newProgress);
            dialog.show();
        }else {
            dialog.setProgress(newProgress);
        }
    }
    private void closeDialog() {
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
            dialog=null;
        }
    }*/

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_BACK){
            if (webView.canGoBack()){
                webView.goBack();
                return true;
            }else {
                System.exit(0);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.forward_image_bt:
                if (webView.canGoForward()){
                    webView.goForward();
                }
                break;
            case R.id.goback_image_bt:
                if (webView.canGoBack()){
                    webView.goBack();
                }
                break;
            case R.id.home_image_bt:
                webView.loadUrl(url);
                break;
        }
    }
}
