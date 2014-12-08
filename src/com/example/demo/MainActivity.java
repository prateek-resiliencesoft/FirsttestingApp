package com.example.demo;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button button;
    public TextView textview;
    public EditText edittext;
    Context context;
    TextView TT;
    
    HttpClient httpclient;
	HttpPost httppost;
	EditText edname, edpassword, edemail, edphone;
	
	String geted1, geted2, geted3;
	
	boolean internetactive;
    
    mobilehttprerquest objmobilehttprerquest=new 	mobilehttprerquest();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
        
        //To Get values from Edit text
        
        edname = (EditText) findViewById(R.id.edUsername);
		edpassword = (EditText) findViewById(R.id.edpassword);
		edemail = (EditText) findViewById(R.id.edEmail);
		
		
    }
    
    
    public void addListenerOnButton()
    {
    	 button=(Button) findViewById(R.id.btnSignup);
    	 httppost = new HttpPost(HttpUrls.HttpUserSignup);
    	 
    	 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				//Assign edittext value to define variable
				geted1 = edname.getText().toString();
				geted2 = edpassword.getText().toString();
				geted3 = edemail.getText().toString();
				
				if (geted1.equals("") || geted2.equals("")
						|| geted3.equals("")) {
					Toast.makeText(MainActivity.this, "Fields are empty",
							Toast.LENGTH_SHORT).show();
				}  else if (geted2.length() < 6) {
					Toast.makeText(MainActivity.this,
							"Password Size is less than 6",
							Toast.LENGTH_SHORT).show();
				} else {

					internetactive = isNetworkAvailable();
					if (internetactive) {
						new getresult().execute();
					} else {
						Toast.makeText(MainActivity.this,
								"Internet Not Connected",
								Toast.LENGTH_SHORT).show();

					}
				}
				
				
				
				
				// TODO Auto-generated method stub
				//
				 //Intent intent=new Intent(context,MainActivity.class);
		       //  startActivity(intent);
				//String value=objmobilehttprerquest.postData();
				//String.. url="http://198.20.168.145:8091/apicalls.asmx/UserSignup";
			//	String[] a = { "http://198.20.168.145:8091/apicalls.asmx/HelloWorld", "world" };
				//String value=objmobilehttprerquest.doInBackground(a);
			//	String value=objmobilehttprerquest.doInBackground(a);
			//	showtoast(value);
				
				
				

			

				
			//	String value= "Hi";//  postData();
		      //  TT.setText(value);
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
				nameValuePairs.add(new BasicNameValuePair("username", geted1));
				nameValuePairs.add(new BasicNameValuePair("password", geted2));
				nameValuePairs.add(new BasicNameValuePair("email", geted3));
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
				if (s.equals("Successfully Registered")) {
					Toast.makeText(MainActivity.this, "Registered Succesfully",
							Toast.LENGTH_SHORT).show();
					finish();
				} else {
					edname.setText("");
					edname.setHint("Username already exists");
					Toast.makeText(MainActivity.this,
							"Username already exists", Toast.LENGTH_SHORT)
							.show();
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
    
    
    
    
//    public String postData() 
//    {
//    	String responseStr = "hello";
//    	try {
//    	// Create a new HttpClient and Post Header
//        HttpClient httpclient = new DefaultHttpClient();
//        HttpPost httppost = new HttpPost("http://198.20.168.145:8091/apicalls.asmx/UserSignup");
//        
//        
//            // Add your data
//         List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(3);
//          nameValuePairs.add(new BasicNameValuePair("username", "para"));
//            nameValuePairs.add(new BasicNameValuePair("password", "para"));
//            nameValuePairs.add(new BasicNameValuePair("email", "param@gmail.com"));
//            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
//
//            // Execute HTTP Post Request
//
//     HttpResponse  response = httpclient.execute(httppost);
//             responseStr = EntityUtils.toString(response.getEntity());
//             
//            
//        } catch (ClientProtocolException e) {
//        	responseStr="clienterror";
//            // TODO Auto-generated catch block
//        } catch (IOException e) {
//        	responseStr=e.getMessage();
//            // TODO Auto-generated catch block
//        }
//    	return responseStr;
//    
//    }
        

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
