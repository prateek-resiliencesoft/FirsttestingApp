package com.example.demo;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DashActivity extends ActionBarActivity implements OnClickListener {
	SharedPreferences shpref;
    HttpClient httpclient;
   	HttpPost httppost;
   	boolean internetactive;
   	String access_token;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash);
		shpref = getSharedPreferences("Office", MODE_PRIVATE);
		try
		{
			findViewById(R.id.Ivprofile).setOnClickListener(this);
			findViewById(R.id.ivsearch).setOnClickListener(this);
			findViewById(R.id.textviewLogout).setOnClickListener(this);
			
		}
		catch(Exception ex)
		{
			ex.getMessage();
		}
		
		
	}
	
	
	public void showtoast(String message)
    {
    	Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dash, menu);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		try
		{
			switch (v.getId()) {
			case R.id.Ivprofile:
				internetactive = isNetworkAvailable();
				if (internetactive) {
					Intent intent=new Intent(DashActivity.this, ProfileActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(DashActivity.this, "Internet Not Connected",
							Toast.LENGTH_SHORT).show();

				}				
				break;	
				
			case R.id.ivsearch:
				internetactive = isNetworkAvailable();
				if (internetactive) {
					Intent intent=new Intent(DashActivity.this, TomorrowActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(DashActivity.this, "Internet Not Connected",
							Toast.LENGTH_SHORT).show();

				}	
				break;	
				
                case R.id.textviewLogout:
				
				internetactive = isNetworkAvailable();
				if (internetactive) {
					SharedPreferences.Editor editor = shpref.edit();
					editor.putBoolean("login", false);
					editor.commit();
					Intent intent=new Intent(DashActivity.this, MainActivity.class);
					startActivity(intent);
					finish();
				} else {
					Toast.makeText(DashActivity.this, "Internet Not Connected",
							Toast.LENGTH_SHORT).show();

				}
				break;
				
		}
			
	}
		catch(Exception ex)
		{
			ex.getMessage();
		}
	}
		
	
	

	private boolean isNetworkAvailable() {
		// TODO Auto-generated method stub

		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}
}
