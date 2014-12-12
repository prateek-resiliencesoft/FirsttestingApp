package com.example.demo;

import java.util.Calendar;

import android.support.v7.app.ActionBarActivity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class TomorrowActivity extends ActionBarActivity {
    Button button;
    EditText fromdate,Enddate,android,net,php;
    static final int DATE_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID1 = 1;
    static final int DATE_DIALOG_ID2 = 2;
    static final int DATE_DIALOG_ID3 = 3;
    private int mYear;
    private int mMonth;
    private int mDay;

    private int mYear1;
    private int mMonth1;
    private int mDay1;
//    private int mYear,mYear1,mYear2,mYear3;
//    private int mMonth,mMonth1,mMonth2,mMonth3;
//    private int mDay,mDay1,mDay2,mDay3;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tomorrow);
		addListenerOnButtonSearch();
		fromdate=(EditText)findViewById(R.id.edFromDate);
		Enddate=(EditText)findViewById(R.id.edTodate);
		
		    final Calendar c = Calendar.getInstance();
		    mYear = c.get(Calendar.YEAR);
		    mMonth = c.get(Calendar.MONTH);
		    mDay = c.get(Calendar.DAY_OF_MONTH);
		    fromdate.setText("From_Date");
		    Enddate.setText("End_Date");
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
//		    android.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			});
//		    net.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			});
//		    php.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					// TODO Auto-generated method stub
//					return false;
//				}
//			});
//		    
//		    Allocatedate.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					// TODO Auto-generated method stub
//					showDialog(DATE_DIALOG_ID2);
//					return false;
//				}
//			});
//		   
//		    completiondate.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					// TODO Auto-generated method stub
//					showDialog(DATE_DIALOG_ID3);
//					return false;
//				}
//			});
		    
		    

		}

		@Override
		protected Dialog onCreateDialog(int id) {
		    switch (id) {
		    case DATE_DIALOG_ID:
		        return new DatePickerDialog(this, mDateSetListener, mYear, mMonth, mDay);

		    case DATE_DIALOG_ID1:
		        return new DatePickerDialog(this, mDateSetListener1, mYear1, mMonth1,mDay1);
		        
//	        case DATE_DIALOG_ID2:
//	             return new DatePickerDialog(this, mDateSetListener2, mYear2, mMonth2,mDay2);
//	        case DATE_DIALOG_ID3:
//		        return new DatePickerDialog(this, mDateSetListener3, mYear3, mMonth3,mDay3);
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
		
//		private void updateDisplay2() {
//
//			Enddate.setText(new StringBuilder()
//		    // Month is 0 based so add 1
//		            .append(mDay2).append("-").append(mMonth2 + 1).append("-").append(mYear2));
//		}
//		
//		private void updateDisplay3() {
//
//			Enddate.setText(new StringBuilder()
//		    // Month is 0 based so add 1
//		            .append(mDay3).append("-").append(mMonth3 + 1).append("-").append(mYear3));
//		}
		
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
//		private DatePickerDialog.OnDateSetListener mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
//		    @Override
//		    public void onDateSet(DatePicker view, int year2, int monthOfYear2,
//		            int dayOfMonth2) {
//		        // TODO Auto-generated method stub
//		        mYear2 = year2;
//		        mMonth2 = monthOfYear2;
//		        mDay2 = dayOfMonth2;
//		        updateDisplay2();
//		    }
//		};
//		
//		private DatePickerDialog.OnDateSetListener mDateSetListener3 = new DatePickerDialog.OnDateSetListener() {
//		    @Override
//		    public void onDateSet(DatePicker view, int year3, int monthOfYear3,
//		            int dayOfMonth3) {
//		        // TODO Auto-generated method stub
//		        mYear3 = year3;
//		        mMonth3 = monthOfYear3;
//		        mDay = dayOfMonth3;
//		        updateDisplay3();
//		    }
//		};
//		
//		
//		
//	
	public void addListenerOnButtonSearch()
    {
    	button=(Button) findViewById(R.id.btnSearch);
    	 button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(TomorrowActivity.this,TaskMessageActivity.class);
				startActivity(intent);
			}
		});
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
}
