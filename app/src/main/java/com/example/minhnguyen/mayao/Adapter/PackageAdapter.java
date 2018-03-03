package com.example.minhnguyen.mayao.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.model.Package;
import java.util.List;

/**
 * Created by Minh Nguyen on 3/3/2018.
 */

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.MyViewHolder> {

    private List<Package> packageList;
    public static int selected_position = 0;

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtSoluongCpu, txtSize,txtMoneymo,txtMemory,txtBan;

        public MyViewHolder(View view) {
            super(view);
            itemView.setOnClickListener(this);
            txtSoluongCpu =  view.findViewById(R.id.txtSoLuongCpu);
            txtSize =  view.findViewById(R.id.txtSize);
            txtMoneymo =  view.findViewById(R.id.txtMoneymo);
            txtMemory =  view.findViewById(R.id.txtMemory);
            txtBan =  view.findViewById(R.id.txtBan);
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

    public PackageAdapter(List<Package> packageList) {
        this.packageList = packageList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.package_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Package aPackage = packageList.get(position);
        holder.txtBan.setText(aPackage.getBandwidth()+"MB");
        holder.txtMemory.setText(aPackage.getMemory()+"MB");
        holder.txtMoneymo.setText("$"+aPackage.getMoneymo());
        holder.txtSize.setText(aPackage.getSize()+"GB");
        holder.txtSoluongCpu.setText(aPackage.getCpu()+" CPU");

        //Item item = items.get(position);

        // Here I am just highlighting the background
        holder.itemView.setBackgroundColor(selected_position == position ? Color.CYAN : Color.TRANSPARENT);

    }

    @Override
    public int getItemCount() {
        return packageList.size();
    }
}
