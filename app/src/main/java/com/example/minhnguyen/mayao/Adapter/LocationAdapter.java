package com.example.minhnguyen.mayao.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.model.Location;
import java.util.List;

/**
 * Created by Minh Nguyen on 3/2/2018.
 */

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {

    private List<Location> locationList;
    public static int selected_position = 0; // You have to set this globally in the Adapter class

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtname, txtname1;

        public MyViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            txtname =  view.findViewById(R.id.txtLocation);
            txtname1 =  view.findViewById(R.id.txtLocation1);
        }

        @Override
        public void onClick(View view) {

            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;

            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);
        }
    }

    public LocationAdapter(List<Location> locationList) {
        this.locationList = locationList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Location location = locationList.get(position);
        holder.txtname.setText(location.getName1());
        holder.txtname1.setText(location.getName2());
        // Here I am just highlighting the background
        holder.itemView.setBackgroundColor(selected_position == position ? Color.CYAN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }
}