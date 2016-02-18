package com.example.samplesnippets;



import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class CardViewActivity extends ActionBarActivity {
	 
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_activity_main);
 
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new CardRecylerAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);
 
        // Code to Add an item with default animation
        //((CardRecylerAdapter) mAdapter).addItem(obj, index);
 
        // Code to remove an item with default animation
        //((CardRecylerAdapter) mAdapter).deleteItem(index);
    }
 
 
    @Override
    protected void onResume() {
        super.onResume();
        ((CardRecylerAdapter) mAdapter).setOnItemClickListener(new CardRecylerAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }
 
    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 20; index++) {
            DataObject obj = new DataObject("Main Text " + index,
                    "Sub Text " + index);
            results.add(index, obj);
        }
        return results;
    }
}