package com.example.minhnguyen.mayao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.minhnguyen.mayao.Adapter.LocationAdapter;
import com.example.minhnguyen.mayao.Adapter.PackageAdapter;
import com.example.minhnguyen.mayao.Adapter.TypeAdapter;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.model.Location;
import com.example.minhnguyen.mayao.model.Package;
import com.example.minhnguyen.mayao.model.ServerType;
import java.util.ArrayList;
import java.util.List;

public class TestRealTime extends AppCompatActivity {

    private List<Location> locationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LocationAdapter mAdapter;

    private List<ServerType> serverTypeList = new ArrayList<>();
    private RecyclerView recyclerViews;
    private TypeAdapter typeAdapter;

    private List<Package> packageList = new ArrayList<>();
    private RecyclerView recyclerViewp;
    private PackageAdapter packageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_real_time);
        recyclerView = (RecyclerView) findViewById(R.id.recycleviewLocation);
        recyclerViews = (RecyclerView) findViewById(R.id.recycleviewType);
        recyclerViewp = (RecyclerView) findViewById(R.id.recycleviewPackage);

        mAdapter = new LocationAdapter(locationList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();

        typeAdapter = new TypeAdapter(serverTypeList);
        RecyclerView.LayoutManager mLayoutManagers = new LinearLayoutManager(getApplicationContext());
        recyclerViews.setLayoutManager(mLayoutManagers);
        recyclerViews.setItemAnimator(new DefaultItemAnimator());
        recyclerViews.setAdapter(typeAdapter);
        prepareMovieDatas();

        packageAdapter = new PackageAdapter(packageList);
        RecyclerView.LayoutManager mLayoutManagerp = new LinearLayoutManager(getApplicationContext());
        recyclerViewp.setLayoutManager(mLayoutManagerp);
        recyclerViewp.setItemAnimator(new DefaultItemAnimator());
        recyclerViewp.setAdapter(packageAdapter);
        prepareMovieDatap();
    }

    private void prepareMovieDatap() {
        Package aPackage = new Package(1,40,10,1,2048,2000);
        packageList.add(aPackage);
        aPackage = new Package(2,80,20,2,4096,4000);
        packageList.add(aPackage);
        aPackage = new Package(1,40,10,1,2048,2000);
        packageList.add(aPackage);
        aPackage = new Package(1,40,10,1,2048,2000);
        packageList.add(aPackage);
        aPackage = new Package(1,40,10,1,2048,2000);
        packageList.add(aPackage);
        packageAdapter.notifyDataSetChanged();

    }

    private void prepareMovieDatas() {
        ServerType serverType = new ServerType(1,"Mad Max: Fury Road", "CentOS ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "Debian ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "Ubuntu ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "Windows ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "CentOS ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "CentOS ", "2015");
        serverTypeList.add(serverType);
        serverType = new ServerType(1,"Mad Max: Fury Road", "CentOS ", "2015");
        serverTypeList.add(serverType);
        typeAdapter.notifyDataSetChanged();
    }

    private void prepareMovieData() {
        Location location = new Location(1,"Mad Max: Fury Road", "Việt Nam", "Hà Nội");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Chicago", "United State");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Los Angeles", "United State");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Việt Nam ", "Hồ Chí Minh");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Việt Nam ", "Hồ Chí Minh");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        location = new Location(1,"Mad Max: Fury Road", "Action ", "2015");
        locationList.add(location);
        mAdapter.notifyDataSetChanged();
    }
}