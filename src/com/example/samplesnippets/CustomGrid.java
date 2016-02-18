package com.example.samplesnippets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 public class CustomGrid extends BaseAdapter{
	 private Context mcontext;
	 private final String[] web;
	 private final int[] imageid;
	 public CustomGrid(Context c,String[] web,int[] imageid)
	 {
		 mcontext=c;
		 this.imageid=imageid;
		 this.web=web;
	 }
		 
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return web.length;
	}
	@Override
	public String getItem(int position) {
		// TODO Auto-generated method stub
		return web[position];
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View grid;
		LayoutInflater inflater=(LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		if(convertView==null){
		grid = new View(mcontext);
             grid = inflater.inflate(R.layout.grid_single, null);
             TextView textView = (TextView) grid.findViewById(R.id.grid_text);
             ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
             textView.setText(getItem(position));
             imageView.setImageResource(imageid[position]);
		}
             else {
				grid=(View)convertView;
			}
		
		return grid;
		
	}
	 
		
	}
 