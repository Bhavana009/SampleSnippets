package com.example.samplesnippets;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btn_list,btn_grid,btn_spinner,btn_date,btn_recycler,btn_card,btn_time,btn_cal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.active_main);
		btn_list=(Button)findViewById(R.id.btn_list);
		btn_grid=(Button)findViewById(R.id.btn_grid);
		btn_spinner=(Button)findViewById(R.id.btn_spinner);
		btn_date=(Button)findViewById(R.id.btn_date);
		btn_recycler=(Button)findViewById(R.id.btn_recycler);
		btn_card=(Button)findViewById(R.id.btn_card);
		btn_time=(Button)findViewById(R.id.btn_time);
		btn_cal=(Button)findViewById(R.id.btn_calendar);
btn_list.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,ListActivity.class);
		startActivity(i);
	}
});
btn_grid.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,GridActivity.class);
		startActivity(i);
	}
});
btn_spinner.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,SpinnerActivity.class);
		startActivity(i);
	}
});
btn_date.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,DatePickerActivity.class);
		startActivity(i);
	}
});
btn_recycler.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,RecyclerViewActivity.class);
		startActivity(i);
	}
});
btn_card.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,CardViewActivity.class);
		startActivity(i);
	}
});
btn_time.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,CustomTimePickerActivity.class);
		startActivity(i);
	}
});
btn_cal.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(MainActivity.this,CalendarView
				.class);
		startActivity(i);
	}
});
}
}
