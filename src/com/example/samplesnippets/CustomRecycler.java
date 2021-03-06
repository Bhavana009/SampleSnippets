package com.example.samplesnippets;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomRecycler extends RecyclerView.Adapter<CustomRecycler.ViewHolder> {
    private ItemData[] itemsData;

    public CustomRecycler(ItemData[] itemsData) {
        this.itemsData = itemsData;
    }
    
    // Create new views (invoked by the layout manager)
    @Override
    public CustomRecycler.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                               .inflate(R.layout.recycle_row, null);

        // create ViewHolder
       
        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        
    	// - get data from your itemsData at this position
        // - replace the contents of the view with that itemsData
    	
    	viewHolder.txtViewTitle.setText(itemsData[position].getTitle());
    	viewHolder.imgViewIcon.setImageResource(itemsData[position].getImageUrl());


    }
    
    // inner class to hold a reference to each item of RecyclerView 
    public static class ViewHolder extends RecyclerView.ViewHolder {
       
    	public TextView txtViewTitle;
        public ImageView imgViewIcon;
        
        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.txt);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.img);
        }
    }

   

   

    // Return the size of your itemsData (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return itemsData.length;
    }
}