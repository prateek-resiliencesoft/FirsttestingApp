package com.example.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;

import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebView.WebViewTransport;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class BrowserActivity extends ActionBarActivity  {
  Button button;
//  HttpClient httpclient;
// 	HttpPost httppost;
 	private WebView webview;
// 	Context context;
// 	private static final int START_AFTER_SECONDS = 10;
 	Uri uri,uri1;
 	String Purl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_browser);
	//{ THIS IS WORKING CODE PARAMATMA SHARAN UPADHYAY
		final Handler h=new Handler();
		final Runnable r=new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
//				Toast.makeText(BrowserActivity.this, "hello Siyasut  Upadhyay",Toast.LENGTH_SHORT).show();
				Uri uri=Uri.parse("http://www.google.com");
				Intent intent=new Intent(Intent.ACTION_VIEW, uri);
				startActivity(intent);
			}
		}; 
		Timer t=new Timer();
		t.scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				h.post(r);
			}
		},6000,5000);
		
		if(uri=="www.google.com")
		{
		
		}
		
		
		
		
		
		
//		
//		try {
//			final InputStream is = new URL("http://www.google.com/").openStream();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
//		String url="http://www.google.com";
//		String Url1="http://www.facebok.com.com";
//		if(url!=Url1)
//		{
//			Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//		}
//		else
//		{
//			Intent intent=new Intent(this, TomorrowActivity.class);
//			startActivity(intent);
//		}
//		
		
		
		
//      uri =Uri.parse("http://www.google.com");
//      uri1 =Uri.parse("http://www.facebok.com");
//        Intent intent = new Intent(Intent.ACTION_VIEW, 	uri);
//		startActivity(intent);
//		Intent intent1=new Intent(Intent.ACTION_VIEW, 	uri1 );
//		startActivity(intent1);
//		if(uri!=uri1)
//		{
//			Intent intent1=new Intent(Intent.ACTION_VIEW, 	uri1 );
//			startActivity(intent1);
//		}
		
		
		
		
		
		
//		webview  =(WebView) findViewById(R.id.Webgoogle);
//		webview.loadUrl("http://google.com");
		
	
//	public void addListenerOnButtonBrowser()
//	{
//		button=(Button) findViewById(R.id.btnbrowser);
////		
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
