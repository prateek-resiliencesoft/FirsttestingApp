package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.demo.MainActivity.getresult;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {
	Button button;
    public TextView textview;
    public EditText edittext;
    Context context;
    TextView TT;
    HttpClient httpclient;
	HttpPost httppost;
	EditText edname, edpassword, edemail, edphone;
	
	String username, password, Email;
	
	boolean internetactive;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		addListenerOnButton();
		
		//Read Edittext values
        edname = (EditText) findViewById(R.id.edloginusername);
		edpassword = (EditText) findViewById(R.id.edloginpassword);
	}

	public void addListenerOnButton()
    {
    	 button=(Button) findViewById(R.id.btnLogin);
    	 httppost = new HttpPost(HttpUrls.HttpUserLogin);
    	 
    	 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Assign edittext value to define variable
				username = edname.getText().toString();
				password = edpassword.getText().toString();
//				Toast.makeText(LoginActivity.this,
//						"password is"+password,
//						Toast.LENGTH_SHORT).show();
			//	Email = edemail.getText().toString();
				
				if (username.equals("") || password.equals("")
						) {
					Toast.makeText(LoginActivity.this, "Fields are empty",
							Toast.LENGTH_SHORT).show();
				} 
				
				else if (password.length() < 6) {
					Toast.makeText(LoginActivity.this,
							"Password Size is less than 6",
							Toast.LENGTH_SHORT).show();
				} 
				
				
				else {

					internetactive = isNetworkAvailable();
					if (internetactive) {
//						Toast.makeText(LoginActivity.this,
//								"1",
//								Toast.LENGTH_SHORT).show();
						new getresult().execute();
					} else {
						Toast.makeText(LoginActivity.this,
								"Internet Not Connected",
								Toast.LENGTH_SHORT).show();

					}
				}
			}
		});
    }
	private Boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetworkInfo = connectivityManager
				.getActiveNetworkInfo();
		return activeNetworkInfo != null
				&& activeNetworkInfo.isConnectedOrConnecting();
	}
	
	public void showtoast(String message)
    {
    	Context context = getApplicationContext();
		CharSequence text = message;
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
    }
	 public class getresult extends AsyncTask<Void, Void, Void> {

			String result = null;

			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				try {
					HttpParams params = new BasicHttpParams();
					params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION,
							HttpVersion.HTTP_1_1);
					httpclient = new DefaultHttpClient(params);
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(
							2);
					nameValuePairs.add(new BasicNameValuePair("username", username));
					nameValuePairs.add(new BasicNameValuePair("password", password));
				//	nameValuePairs.add(new BasicNameValuePair("email", Email));
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,
							"utf-8"));
					result = httpclient.execute(httppost,
							new BasicResponseHandler());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result1) {
				// TODO Auto-generated method stub
				super.onPostExecute(result1);
				int duration = Toast.LENGTH_SHORT;
				try {
					JSONObject jsonObj = new JSONObject(result);
					String access_token = "" + jsonObj.get("AccessToken");
				    String s = "" + jsonObj.get("Message");
					if (s.equals("Successfully User LoggedIn")) {
					Toast.makeText(LoginActivity.this, "LoggedIn Successfully",
								Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(LoginActivity.this,ProfileActivity.class);
					startActivity(intent);
						finish();
						
						SharedPreferences shpref = getSharedPreferences(
								"Office", MODE_PRIVATE);
						SharedPreferences.Editor editor = shpref.edit();
						editor.putString("access_token", access_token);
						editor.putBoolean("login", true);
						editor.commit();
					} else {
						edname.setText("");
						edname.setHint("Failed to login");
						Toast.makeText(LoginActivity.this, "Failed to login", Toast.LENGTH_SHORT).show();
						Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);
						v.vibrate(1000);
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	 @Override
		public void onBackPressed() {
			// TODO Auto-generated method stub
			super.onBackPressed();
			try {
				startActivity(new Intent(LoginActivity.this, MainActivity.class));
				finish();
			} catch (Exception e) {

			}
		}

	    
   	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
