package com.example.minhnguyen.mayao.Adapter;

/**
 * Created by Minh Nguyen on 3/3/2018.
 */

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.model.ServerType;
import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter<TypeAdapter.MyViewHolder> {

    private List<ServerType> serverTypeList;
    public static int selected_position = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;

        public MyViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            name = (TextView) view.findViewById(R.id.txtName);
        }

        @Override
        public void onClick(View view) {
            // Below line is just like a safety check, because sometimes holder could be null,
            // in that case, getAdapterPosition() will return RecyclerView.NO_POSITION
            if (getAdapterPosition() == RecyclerView.NO_POSITION) return;
            // Updating old as well as new positions
            notifyItemChanged(selected_position);
            selected_position = getAdapterPosition();
            notifyItemChanged(selected_position);
            // Do your another stuff for your onClick

        }
    }


    public TypeAdapter(List<ServerType> serverTypes) {
        this.serverTypeList = serverTypes;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stype_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        ServerType serverType = serverTypeList.get(position);
        holder.name.setText(serverType.getName());
        holder.itemView.setBackgroundColor(selected_position == position ? Color.CYAN : Color.TRANSPARENT);
    }

    @Override
    public int getItemCount() {
        return serverTypeList.size();
    }
}