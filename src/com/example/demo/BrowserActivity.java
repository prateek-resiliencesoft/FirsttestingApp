package com.example.demo;

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
  HttpClient httpclient;
 	HttpPost httppost;
 	private WebView webview;
 	Context context;
 	private static final int START_AFTER_SECONDS = 10;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		setContentView(R.layout.activity_browser);
		
		move_next();
		
//		Intent intent = new Intent(Intent.ACTION_VIEW, 	 Uri.parse("http://www.google.com"));
//		startActivity(intent);
		
//		webview  =(WebView) findViewById(R.id.Webgoogle);
//		webview.loadUrl("http://google.com");
//		TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                Toast wait = Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_LONG);
////                wait.setGravity(Gravity.TOP, 0, 0);
////                wait.show();
//            }
//        };
//        Timer timer = new Timer();
//        timer.schedule(timerTask, 2000, 2000);

    }
		
	
	public void move_next()
	{
	final Handler handle = new Handler();
	        Runnable delay = new Runnable() {
	            public void run() 
	            {  
	            	Intent intent = new Intent(Intent.ACTION_VIEW, 	 Uri.parse("http://www.google.com"));
	           	Toast.makeText(BrowserActivity.this, "hello Siyasut Paramatma Sharan Upadhyay",
							Toast.LENGTH_SHORT).show();
	            	BrowserActivity.this.startService(intent);
	            }
	        };
	        handle.postDelayed(delay,START_AFTER_SECONDS*60);
	}
	
		
//		new Handler().postDelayed(new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				Uri uri= Uri.parse("http://www.google.com");
//				
//				final Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//				startActivity(intent);
//				BrowserActivity.this.closeContextMenu();
//			}
//		},5000);
//		
		
		
		
		


//           new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                final Intent mainIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
//                BrowserActivity.this.startActivity(mainIntent);
//                BrowserActivity.this.finish();
//            }
//        }, 5000);
		
		//addListenerOnButtonBrowser();
		



	
	
	
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
