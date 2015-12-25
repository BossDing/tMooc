package com.example.tmooc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class SeeVideo extends Activity {
	private String PLAYURL;
	private WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seevideo);
		
		Intent  i = getIntent();
		PLAYURL = i.getStringExtra("URL");
		webView = (WebView) findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){

			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		     
		});
		
		
		webView.loadUrl(PLAYURL);
		
	}

}
