package com.example.samplesnippets;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
 
public class GridActivity extends Activity {
    GridView grid;
    String[] web = {
            "Calendar",
            "camera",
            "clock",
            "music",
            "dail",
            "SMS",
            "Gallery",
            "Calendar",
            "camera"
 
    } ;
    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image1,
            R.drawable.image2
            
            
 
    };
    ImageView back_arrow;
    @SuppressWarnings("unused")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_activity_main);
        back_arrow=(ImageView)findViewById(R.id.backarrow);
        CustomGrid adapter = new CustomGrid(GridActivity.this, web, imageId);
        if(adapter!=null)
        	System.out.println("not null");
        else
        	System.out.println("null");
        grid=(GridView)findViewById(R.id.grid);
                grid.setAdapter(adapter);
                grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Toast.makeText(GridActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                       
                    }
                });
                back_arrow.setOnClickListener(new OnClickListener() {
                	
                	@Override
                	public void onClick(View v) {
                		// TODO Auto-generated method stub
                		 Intent i=new Intent(GridActivity.this,MainActivity.class);
                         startActivity(i);
                	}
                });
    }
 
}