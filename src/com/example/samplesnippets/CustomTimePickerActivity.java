package com.example.samplesnippets;

import java.util.Calendar;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
 
public class CustomTimePickerActivity extends Activity implements OnClickListener{
  
    private EditText txt_time=null;
    private Button btn_time=null;
    ImageView back_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timepicker);
        
        back_arrow=(ImageView)findViewById(R.id.backarrow);
        txt_time=(EditText)findViewById(R.id.editText_time);
        btn_time=(Button)findViewById(R.id.button_time);
        btn_time.setOnClickListener(this);
        back_arrow.setOnClickListener(new OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		Intent i=new Intent(CustomTimePickerActivity.this,MainActivity.class);
                startActivity(i);
        	}
        });
    }
    @Override
    public void onClick(View arg0) {
         CustomTimePickerDialog timePickerDialog = new CustomTimePickerDialog(CustomTimePickerActivity.this, timeSetListener, 
                    Calendar.getInstance().get(Calendar.HOUR), 
                    CustomTimePickerDialog.getRoundedMinute(Calendar.getInstance().get(Calendar.MINUTE) + CustomTimePickerDialog.TIME_PICKER_INTERVAL), true);
        timePickerDialog.setTitle("Set hours and minutes");
        timePickerDialog.show();
    }
     
     public static class CustomTimePickerDialog extends TimePickerDialog{
 
            public static final int TIME_PICKER_INTERVAL=1;
            private boolean mIgnoreEvent=false;
 
            public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
            super(context, callBack, hourOfDay, minute, is24HourView);
            }
     
            @Override
            public void onTimeChanged(TimePicker timePicker, int hourOfDay, int minute) {
                super.onTimeChanged(timePicker, hourOfDay, minute);
                if (!mIgnoreEvent){
                    minute = getRoundedMinute(minute);
                    mIgnoreEvent=true;
                    timePicker.setCurrentMinute(minute);
                    mIgnoreEvent=false;
                }
            }
 
            public static  int getRoundedMinute(int minute){
                 if(minute % TIME_PICKER_INTERVAL != 0){
                    int minuteFloor = minute - (minute % TIME_PICKER_INTERVAL);
                    minute = minuteFloor + (minute == minuteFloor + 1 ? TIME_PICKER_INTERVAL : 0);
                    if (minute == 60)  minute=0;
                 }
 
                return minute;
            }
        }
      
     private CustomTimePickerDialog.OnTimeSetListener timeSetListener = new CustomTimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                txt_time.setText(String.format("%02d", hourOfDay) + ":" +String.format("%02d", minute));
            }
      };
}
