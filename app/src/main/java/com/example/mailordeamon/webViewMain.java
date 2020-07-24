package com.example.mailordeamon;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class webViewMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_main);
        WebView webView=findViewById(R.id.web_view);
        String site=getIntent().getStringExtra("site");
        //String link=getIntent().getStringExtra("link");

        webView.loadUrl(site);

        ProgressBar progressBar=findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

    }
}
