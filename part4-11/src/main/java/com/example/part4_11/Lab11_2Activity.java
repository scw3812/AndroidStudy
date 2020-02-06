package com.example.part4_11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class Lab11_2Activity extends AppCompatActivity implements View.OnClickListener{

    Button lineBtn, barBtn;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab11_2);

        webView = (WebView)findViewById(R.id.webview);
        lineBtn = (Button)findViewById(R.id.btn_chart_line);
        barBtn = (Button)findViewById(R.id.btn_chart_bar);
        lineBtn.setOnClickListener(this);
        barBtn.setOnClickListener(this);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/test.html");

        webView.addJavascriptInterface(new JavascriptTest(), "android");
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new MyWebChrome());
    }

    @Override
    public void onClick(View view) {
        if(view == lineBtn){
            webView.loadUrl("javascript:lineChart()");
        }else if(view == barBtn){
            webView.loadUrl("javascript:barChart()");
        }
    }

    class JavascriptTest{
        @JavascriptInterface
        public String getChartData(){
            StringBuffer buffer = new StringBuffer();
            buffer.append("[");
            for(int i = 0; i < 14; i++){
                buffer.append("["+ i + "," + Math.sin(i) + "]");

                if(i < 13) buffer.append(",");
            }
            buffer.append("]");
            return buffer.toString();
        }
    }

    class MyWebClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(Lab11_2Activity.this, url, Toast.LENGTH_SHORT).show();

            return true;
        }
    }

    class MyWebChrome extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(Lab11_2Activity.this, message, Toast.LENGTH_SHORT).show();
            result.confirm();
            return true;
        }
    }
}
