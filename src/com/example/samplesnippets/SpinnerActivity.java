package com.example.samplesnippets;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class SpinnerActivity extends Activity {
	// declare three arrays for content of spinner
	String[] strings = { "Calender Icon", "Camera Icon", "Clock Icon",
			"Music Icon", "Dailer Icon", "SMS Icon" };

	String[] subs = { "Calender", "Camera", "Clock", "Music", "Dailer", "SMS" };

	int arr_images[] = { R.drawable.image1, R.drawable.image2,
			R.drawable.image3, R.drawable.image4, R.drawable.image5,
			R.drawable.image6 };

	TextView textview;
	Spinner spinner;
	ImageView back_arrow;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spinner_activity_main);
		 back_arrow=(ImageView)findViewById(R.id.backarrow);
		textview = (TextView) findViewById(R.id.textView);
		spinner = (Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(new MyAdapter(this, R.layout.spinner_row, strings));
		
		
		  back_arrow.setOnClickListener(new OnClickListener() {
          	
          	@Override
          	public void onClick(View v) {
          		// TODO Auto-generated method stub
          		 Intent i=new Intent(SpinnerActivity.this,MainActivity.class);
                 startActivity(i);
          	}
          });
	}

	// button onclick
	// public void button_click(View view) {
	// textview.setText(spinner.getSelectedItem().toString());
	// }
	// Adapter class for spinner control
	public class MyAdapter extends ArrayAdapter<String> {

		public MyAdapter(Context context, int textViewResourceId,
				String[] objects) {
			super(context, textViewResourceId, objects);
		}

		@Override
		public View getDropDownView(int position, View convertView,
				ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getCustomView(position, convertView, parent);
		}

		public View getCustomView(int position, View convertView,
				ViewGroup parent) {

			LayoutInflater inflater = getLayoutInflater();
			View row = inflater.inflate(R.layout.spinner_row, parent, false);
			TextView label = (TextView) row.findViewById(R.id.company);
			label.setText(strings[position]);
			
			TextView sub = (TextView) row.findViewById(R.id.sub);
			sub.setText(subs[position]);

			ImageView icon = (ImageView) row.findViewById(R.id.image);
			icon.setImageResource(arr_images[position]);
			 textview.setText(spinner.getSelectedItem().toString());
			return row;
			
		}
	}
}