package com.example.samplesnippets;

import java.util.Calendar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DatePickerActivity extends Activity {

   private DatePicker datePicker;
   private Calendar calendar;
   private TextView dateView;
   private int year, month, day;
   ImageView back_arrow;
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.date_activity);
      back_arrow=(ImageView)findViewById(R.id.backarrow);
      dateView = (TextView) findViewById(R.id.textView3);
      calendar = Calendar.getInstance();
      year = calendar.get(Calendar.YEAR);
      
      month = calendar.get(Calendar.MONTH);
      day = calendar.get(Calendar.DAY_OF_MONTH);
      showDate(year, month+1, day);
      back_arrow.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		 Intent i=new Intent(DatePickerActivity.this,MainActivity.class);
                 startActivity(i);
        	}
        });
   }

   @SuppressWarnings("deprecation")
   public void setDate(View view) {
      showDialog(999);
      Toast.makeText(getApplicationContext(), "calendar", Toast.LENGTH_SHORT)
      .show();
   }

   @Override
   protected Dialog onCreateDialog(int id) {
      // TODO Auto-generated method stub
      if (id == 999) {
         return new DatePickerDialog(this, myDateListener, year, month, day);
      }
      return null;
   }

   private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
      @Override
      public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3) {
         // TODO Auto-generated method stub
         // arg1 = year
         // arg2 = month
         // arg3 = day
         showDate(arg1, arg2+1, arg3);
      }
   };

   private void showDate(int year, int month, int day) {
      dateView.setText(new StringBuilder().append(day).append("/")
      .append(month).append("/").append(year));
   }

   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
}