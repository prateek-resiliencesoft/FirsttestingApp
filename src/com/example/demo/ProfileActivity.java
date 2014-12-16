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



import android.R.string;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends ActionBarActivity implements OnClickListener {
	public TextView textview;
    public EditText edittext;
    SharedPreferences shpref;
    Button button;
    Context context;
    HttpClient httpclient;
   	HttpPost httppost;
   	EditText edfulname, edaddress, edcontact, edqualification,eddesignation,eddepartment;
   	
   	String Username, address, contact,qualification,designation,department,access_token;
   	boolean internetactive;
   
    mobilehttprerquest objmobilehttprerquest=new 	mobilehttprerquest();


  // SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		shpref = getSharedPreferences("Office", MODE_PRIVATE);
		
		
		addListenerOnButtonUpdate();
		try{
		edfulname=(EditText) findViewById(R.id.edEmpname);
		edaddress=(EditText) findViewById(R.id.edEmpAddress);
		edcontact=(EditText) findViewById(R.id.edContact);
		edqualification=(EditText) findViewById(R.id.edQualification);
		eddesignation=(EditText) findViewById(R.id.edDesignation);
		eddepartment=(EditText) findViewById(R.id.edDepartment);
		findViewById(R.id.textlgout).setOnClickListener(this);
		
		
//		ProfileItems.access_token = shpref.getString("access_token", "aa");
//		httppost = new HttpPost(HttpUrls.HttpProfileUpdate);
//		internetactive = isNetworkAvailable();
//		if (internetactive) {
//			new getresult().execute();
//		} else {
//			Toast.makeText(ProfileActivity.this, "Internet Not Connected",
//					Toast.LENGTH_SHORT).show();

//		}
		}
		catch(Exception ex)
		{
			
		}
	}
	
	
	public void addListenerOnButtonUpdate()
    {
    	 button=(Button) findViewById(R.id.btnUpdate);
    	 httppost = new HttpPost(HttpUrls.HttpProfileUpdate);
    	 
    	 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				//Assign edittext value to define variable
				Username = edfulname.getText().toString();
				address = edaddress.getText().toString();
				contact = edcontact.getText().toString();
				qualification=edqualification.getText().toString();
				designation=eddesignation.getText().toString();
				department=eddepartment.getText().toString();
				try		{
					
					access_token=shpref.getString("access_token", "aa");
					Toast.makeText(ProfileActivity.this, "access_token is " +access_token,
							Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.d("Error", ex.getMessage());
					Toast.makeText(ProfileActivity.this, "access_token is " +ex.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
//				
//				Toast.makeText(ProfileActivity.this, "access_token is "+ access_token,
//						Toast.LENGTH_SHORT).show();
				
				if (Username.equals("") || address.equals("")
						|| contact.equals("")||qualification.equals("")||designation.equals("")||department.equals("")) {
					Toast.makeText(ProfileActivity.this, "Fields are empty",
							Toast.LENGTH_SHORT).show();
				
				} else {

					internetactive = isNetworkAvailable();
					if (internetactive) {
						new getresult().execute();
					} else {
						Toast.makeText(ProfileActivity.this,
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
							6);
					//nameValuePairs.add(new BasicNameValuePair("Fullname", Username));
					nameValuePairs.add(new BasicNameValuePair("empAddress", address));
				    nameValuePairs.add(new BasicNameValuePair("contact", contact));
				    nameValuePairs.add(new BasicNameValuePair("qualification", qualification));
				    nameValuePairs.add(new BasicNameValuePair("designation", designation));
				    nameValuePairs.add(new BasicNameValuePair("department", department));
				    nameValuePairs.add(new BasicNameValuePair("accesstoken", access_token));
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
				try {
					JSONObject jsonObj = new JSONObject(result);
					String s = "" + jsonObj.get("Message");
					String Error = "" + jsonObj.get("Type");
					if (s.equals("Successfully ")) {
						Toast.makeText(ProfileActivity.this, "Updated Succesfully",
								Toast.LENGTH_SHORT).show();
						finish();
					} else {
//						Username.setText("");
//						Username.setHint("Username already exists");
						Toast.makeText(ProfileActivity.this,
								"Failed "+Error, Toast.LENGTH_SHORT)
								.show();
						
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
	    
	    public void onClick(View v) {
	    	
	    	
	    	try{
	    		switch(v.getId()){
	    		case R.id.textlgout:
	    			internetactive = isNetworkAvailable();
				if (internetactive) {
					 shpref = getSharedPreferences("Office", MODE_PRIVATE);
						SharedPreferences.Editor editor = shpref.edit();
						editor.putBoolean("login", false);
						editor.commit();
						startActivity(new Intent(ProfileActivity.this, MainActivity.class));
						finish();
					} 
					else {
						Toast.makeText(ProfileActivity.this, "Internet Not Connected",
								Toast.LENGTH_SHORT).show();
					}
					break;
	    		}
	    	}
	    	catch(Exception ex)
	    	{
	    		
	    	}
	    }
			
	    public void addListenerOnButtonlogout()
	    {
	    	 button=(Button) findViewById(R.id.btnLogout);
	    	 button.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try{
						JSONObject jsonObj = new JSONObject("");
						String access_token = "" + jsonObj.get("AccessToken");
			    			internetactive = isNetworkAvailable();
						if (internetactive) {
							SharedPreferences shpref = getSharedPreferences("Office", MODE_PRIVATE);
								SharedPreferences.Editor editor = shpref.edit();
								editor.putBoolean("login", false);
								editor.commit();
								startActivity(new Intent(ProfileActivity.this, MainActivity.class));
								finish();
							} 
							else {
								Toast.makeText(ProfileActivity.this, "Internet Not Connected",
										Toast.LENGTH_SHORT).show();
							}
						
			    		
			    	}
			    	catch(Exception ex)
			    	{
			    		
			    	}
				}
			});
	    
	    }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
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
