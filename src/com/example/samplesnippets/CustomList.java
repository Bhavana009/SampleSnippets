package com.example.samplesnippets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class CustomList extends BaseAdapter{
 
private final Context mContext;
private final String[] web;
private final int[] imageId;
public CustomList(Context c,String[] web,int[] imageid)
{
	mContext=c;
	 this.imageId=imageid;
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
	View list;
	LayoutInflater inflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	if(convertView==null){
	list = new View(mContext);
        list = inflater.inflate(R.layout.list_single, null);
        TextView textView = (TextView) list.findViewById(R.id.txt);
        ImageView imageView = (ImageView)list.findViewById(R.id.img);
        textView.setText(getItem(position));
        imageView.setImageResource(imageId[position]);
	}
        else {
			list=(View)convertView;
		}
	
	return list;
	
}
}