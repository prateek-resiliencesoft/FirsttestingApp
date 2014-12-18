package com.example.demo;

import java.util.ArrayList;
import java.util.Calendar;
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
import android.support.v7.app.ActionBarActivity;
import android.test.IsolatedContext;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public  class TomorrowActivity extends ActionBarActivity implements OnClickListener  {    
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID1 = 1;
    SharedPreferences shpref;
    HttpClient httpclient;
   	HttpPost httppost;
   	Button button;
    Context context;
    EditText fromdate,Enddate;
    private int mYear;
    private int mMonth;
    private int mDay;

    private int mYear1;
    private int mMonth1;
    private int mDay1;
    String access_token,begindate,enddate;
    boolean internetactive;
    
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tomorrow);
		shpref = getSharedPreferences("Office", MODE_PRIVATE);
		addListenerOnButtonSearch();				
		fromdate=(EditText)findViewById(R.id.edfromdate);
		Enddate=(EditText)findViewById(R.id.edtodate);
		findViewById(R.id.Logouttext).setOnClickListener(this);
		findViewById(R.id.txtbacktoDashboard).setOnClickListener(this);
		    final Calendar c = Calendar.getInstance();
		    mYear = c.get(Calendar.YEAR);
		    mMonth = c.get(Calendar.MONTH);
		    mDay = c.get(Calendar.DAY_OF_MONTH);
		    fromdate.setText("From_Date");
		    Enddate.setText("End_Date");
//		    try{
//		    	findViewById(R.id.Logouttext).setOnClickListener(this);
//		    }
//		    catch(Exception ex)
//		    {
//		    	ex.getMessage();
//		    }
		    fromdate.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					showDialog(DATE_DIALOG_ID);
					return false;
				}
			});

		    Enddate.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					// TODO Auto-generated method stub
					showDialog(DATE_DIALOG_ID1);
					return false;
				}
			});
		
		}

		@Override
		protected Dialog onCreateDialog(int id) {
		    switch (id) {
		    case DATE_DIALOG_ID:
		        return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);

		    case DATE_DIALOG_ID1:
		        return new DatePickerDialog(this, mDateSetListener1, mYear1, mMonth1,mDay1);
		    }
		    return null;
		}

		// updates the date in the TextView

		private void updateDisplay() {
			fromdate.setText(new StringBuilder()
		    // Month is 0 based so add 1
		            .append(mDay).append("-").append(mMonth + 1).append("-").append(mYear));
		}
		private void updateDisplay1() {

			Enddate.setText(new StringBuilder()
		    // Month is 0 based so add 1
		            .append(mDay1).append("-").append(mMonth1 + 1).append("-").append(mYear1));
		}
		
		// the callback received when the user "sets" the date in the dialog
		private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year, int monthOfYear,
		            int dayOfMonth) {
		        // TODO Auto-generated method stub
		        mYear = year;
		        mMonth = monthOfYear;
		        mDay = dayOfMonth;
		        updateDisplay();
		    }
		};

		private DatePickerDialog.OnDateSetListener mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
		    @Override
		    public void onDateSet(DatePicker view, int year1, int monthOfYear1,
		            int dayOfMonth1) {
		        // TODO Auto-generated method stub
		        mYear1 = year1;
		        mMonth1 = monthOfYear1;
		        mDay1 = dayOfMonth1;
		        updateDisplay1();
		    }
		};
	public void addListenerOnButtonSearch()
    {
    	button=(Button) findViewById(R.id.btnsearch);
    	httppost = new HttpPost(HttpUrls.httpsearch);
    	 button.setOnClickListener(new OnClickListener() {
    		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				begindate = fromdate.getText().toString();
				enddate = Enddate.getText().toString();
				access_token=shpref.getString("access_token", "aa");
//				Intent intent=new Intent(TomorrowActivity.this,RecordShow.class);
//				startActivity(intent);
              try{
					
					access_token=shpref.getString("access_token", "aa");
					Toast.makeText(TomorrowActivity.this, "access_token is " +access_token,
							Toast.LENGTH_SHORT).show();
				}
				catch(Exception ex)
				{
					Log.d("Error", ex.getMessage());
					Toast.makeText(TomorrowActivity.this, "access_token is " +ex.getMessage(),
							Toast.LENGTH_SHORT).show();
				}
			}
		});
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
						3);
				nameValuePairs.add(new BasicNameValuePair("begindate", begindate));		   
			    nameValuePairs.add(new BasicNameValuePair("enddate:", enddate));
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
	
	
	
	 public void showtoast(String message)
	    {
	    	Context context = getApplicationContext();
			CharSequence text = message;
			int duration = Toast.LENGTH_SHORT;

			Toast toast = Toast.makeText(context, text, duration);
			toast.show();
	    }

	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tomorrow, menu);
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
		try{
    		switch(v.getId()){
    		case R.id.Logouttext:
    			internetactive = isNetworkAvailable();
			if (internetactive) {
				 shpref = getSharedPreferences("Office", MODE_PRIVATE);
					SharedPreferences.Editor editor = shpref.edit();
					editor.putBoolean("login", false);
					editor.commit();
					startActivity(new Intent(TomorrowActivity.this, MainActivity.class));
					finish();
				} 
				else {
					Toast.makeText(TomorrowActivity.this, "Internet Not Connected",
							Toast.LENGTH_SHORT).show();
				}
				break;
    		case R.id.txtbacktoDashboard:
    			internetactive=isNetworkAvailable();
    			if(internetactive)
    			{
    				Intent intent=new Intent(TomorrowActivity.this, DashActivity.class);
    				startActivity(intent);
    				finish();
    			}
    			else
    			{
    				Toast.makeText(TomorrowActivity.this, "Internet Not Connected",
							Toast.LENGTH_SHORT).show();
    			}
    		}
    	}
    	catch(Exception ex)
    	{
    		
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



	
