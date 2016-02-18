package com.example.samplesnippets;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.app.Activity;
import android.os.Bundle;

public class RecyclerViewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity_main);
        
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        
        
        ItemData itemsData[] = { new ItemData("Calendar",R.drawable.image1),
        						 new ItemData("Camera",R.drawable.image2),
        						 new ItemData("Clock",R.drawable.image3),
        						new ItemData("Music",R.drawable.image4),
        						new ItemData("Phone",R.drawable.image5),
        						new ItemData("SMS",R.drawable.image6)};
       
        
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        CustomRecycler mAdapter = new CustomRecycler(itemsData);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }
}