package com.example.demo;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;

public class BrowserActivity extends ActionBarActivity  {
  Button button;
  HttpClient httpclient;
 	HttpPost httppost;
 	WebView webview;
 	Context context;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_browser);
//		Intent intent = new Intent(Intent.ACTION_VIEW, 	 Uri.parse("http://www.google.com"));
//		startActivity(intent);
		
		webview  =(WebView) findViewById(R.id.Webgoogle);
		webview.loadUrl("http://google.com");
		
//		addListenerOnButtonBrowser();
	}

	
	
//	public void addListenerOnButtonBrowser()
//	{
//		button=(Button) findViewById(R.id.btnbrowser);
////		httppost = new HttpPost(HttpUrls.Url);
//		button.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent = new Intent(Intent.ACTION_VIEW, 	 Uri.parse("http://www.google.com"));
//				startActivity(intent);
//			    
//
//			
//			}
//		});
//	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.browser, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}



	
}
