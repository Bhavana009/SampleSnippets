package com.example.samplesnippets;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
 
public class ListActivity extends Activity {
    ListView list;
    String[] web = {
        "Calendar",
            "Camera",
            "Clock",
            "Music",
            "Dail",
            "SMS",
            "Photos"
    } ;
    int[] imageId = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7
 
    };
 ImageView back_arrow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity_main);
 back_arrow=(ImageView)findViewById(R.id.backarrow);
        CustomList adapter = new
                CustomList(ListActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Toast.makeText(ListActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                        
 
                    }
                });
 back_arrow.setOnClickListener(new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent i=new Intent(ListActivity.this,MainActivity.class);
        startActivity(i);
	}
});
    }
 
}
