package com.example.demo;

import android.support.v7.app.ActionBarActivity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class EmailActivity extends ActionBarActivity {
  EditText edto,edsubject,edmessage;
  Button btnsend;
  String to,subject,message;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_email);
		Sendmail();
		
		edto = (EditText) findViewById(R.id.edEmailAddress);
		edsubject = (EditText) findViewById(R.id.edemailSubject);
		edmessage = (EditText) findViewById(R.id.edemialmessage);	
		
		
	}
	
	public void Sendmail()
	{ btnsend = (Button) findViewById(R.id.btnSendmail);
	btnsend.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			 to = edto.getText().toString();
		     subject = edsubject.getText().toString();
			  message = edmessage.getText().toString();
			  //Intent email=new Intent(Intent.ACTION_SEND);
			  Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto","paramatmasharan@gmail.com", null));
			  emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_CC, new String[]{ to});
			  //email.putExtra(Intent.EXTRA_BCC, new String[]{to});
			  emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			  emailIntent.putExtra(Intent.EXTRA_TEXT, message);
			  emailIntent.setType("plain/text");
			  startActivity(Intent.createChooser(emailIntent, "Choose an Email client:"));
			 
		}
		
	});
		
	} 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.email, menu);
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
