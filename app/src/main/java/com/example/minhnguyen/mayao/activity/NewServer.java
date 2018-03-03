package com.example.minhnguyen.mayao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import com.example.minhnguyen.mayao.Adapter.LocationAdapter;
import com.example.minhnguyen.mayao.Adapter.PackageAdapter;
import com.example.minhnguyen.mayao.Adapter.TypeAdapter;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.RecyclerItemClickListener;
import com.example.minhnguyen.mayao.model.Location;
import com.example.minhnguyen.mayao.model.Package;
import com.example.minhnguyen.mayao.model.Server;
import com.example.minhnguyen.mayao.model.ServerType;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NewServer extends AppCompatActivity {
    Button btnThem;

    private DatabaseReference myref;

    CheckBox chkIp,chkBackup,chkProtect,chkPrivate;
    EditText edtname,edtlabel;

    private List<Location> locationList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LocationAdapter mAdapter;

    private List<ServerType> serverTypeList = new ArrayList<>();
    private RecyclerView recyclerViews;
    private TypeAdapter typeAdapter;

    private List<Package> packageList = new ArrayList<>();
    private RecyclerView recyclerViewp;
    private PackageAdapter packageAdapter;

    //ArrayList<Server> servers = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_server);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AnhXa();
        recycle();
        final int soluongserver = (int) getIntent().getSerializableExtra("soluongserver");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        btnThem = findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"Id: "+soluongserver+
                        "Location: "+locationList.get(LocationAdapter.selected_position).getName1()+"\n"+
                        "Size: "+packageList.get(PackageAdapter.selected_position).getSize()+"\n"+"Server Type: "
                        +serverTypeList.get(TypeAdapter.selected_position).getName()+"\n"+
                        "Label:"+edtlabel.getText().toString()+"\n"
                        +"Name:"+edtname.getText().toString(),Toast.LENGTH_LONG).show();
                if (chkBackup.isChecked()) Toast.makeText(getApplicationContext(),chkBackup.getText().toString(),Toast.LENGTH_LONG).show();
                if (chkIp.isChecked()) Toast.makeText(getApplicationContext(),chkIp.getText().toString(),Toast.LENGTH_LONG).show();
                if (chkPrivate.isChecked()) Toast.makeText(getApplicationContext(),chkPrivate.getText().toString(),Toast.LENGTH_LONG).show();
                if (chkProtect.isChecked()) Toast.makeText(getApplicationContext(),chkProtect.getText().toString(),Toast.LENGTH_LONG).show();

                Server server = new Server(soluongserver+1,edtlabel.getText().toString(),edtname.getText().toString(),
                        locationList.get(LocationAdapter.selected_position).getName1(),serverTypeList.get(TypeAdapter.selected_position).getName(),
                        packageList.get(PackageAdapter.selected_position).getSize(),84,54,94,1,1);
                myref = FirebaseDatabase.getInstance().getReference();
                myref.child("server").push().setValue(server);
                Toast.makeText(getApplicationContext(),"Thêm mới server thành công",Toast.LENGTH_LONG).show();

            }
        });
    }

    private void AnhXa() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleviewLocation);
        recyclerViews = (RecyclerView) findViewById(R.id.recycleviewType);
        recyclerViewp = (RecyclerView) findViewById(R.id.recycleviewPackage);
        edtname = findViewById(R.id.edtName);
        edtlabel = findViewById(R.id.edtLabel);
        chkBackup = findViewById(R.id.chkBackup);
        chkIp = findViewById(R.id.chkIp);
        chkPrivate = findViewById(R.id.chkPrivate);
        chkProtect = findViewById(R.id.chkProtect);
    }

    private void recycle() {
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

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),locationList.get(position).getName1(),Toast.LENGTH_LONG).show();
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerViews.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViews ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),serverTypeList.get(position).getName(),Toast.LENGTH_LONG).show();
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );

        recyclerViewp.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerViewp ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),packageList.get(position).getSize()+" GB",Toast.LENGTH_LONG).show();
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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
