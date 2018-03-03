package com.example.minhnguyen.mayao.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.minhnguyen.mayao.R;
import com.example.minhnguyen.mayao.RecyclerItemClickListener;
import com.example.minhnguyen.mayao.model.Customer;
import com.example.minhnguyen.mayao.model.Server;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Customer> customers = new ArrayList<>();
    public static ArrayList<Server> servers = new ArrayList<>();
    RecyclerView recyclerView;
    private DatabaseReference myref;
    TextView txtThongBao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleview);



        setdulieuserver();
        setdulieucustom();

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Thêm mới máy ảo",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this,NewServer.class);
                intent.putExtra("soluongserver",servers.size());
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        myref= FirebaseDatabase.getInstance().getReference().child("/server");
        FirebaseRecyclerAdapter<Server,ServerViewHolder> recyclerAdapter=new FirebaseRecyclerAdapter<Server,ServerViewHolder>(
                Server.class,
                R.layout.server_row,
                ServerViewHolder.class,
                myref

        ) {
            @Override
            protected void populateViewHolder(ServerViewHolder viewHolder, Server model, int position) {
                //viewHolder.setId(String.valueOf(model.getId()));
                viewHolder.setCpu(String.valueOf(model.getCpu()));
                viewHolder.setDisk(String.valueOf(model.getDisk()));
                viewHolder.setRam(String.valueOf(model.getRam()));
                viewHolder.setType(String.valueOf(model.getType()));
                viewHolder.setLabel(String.valueOf(model.getLabel()));
                viewHolder.setLoacation(String.valueOf(model.getLocation()));
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent intent = new Intent(MainActivity.this,Detail.class);
                        intent.putExtra("thongtinserver",servers.get(position).getId());
                        startActivity(intent);
                    }
                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        MenuItem iThongBao = menu.findItem(R.id.itThongBao);
        View giaoDienThongBao = MenuItemCompat.getActionView(iThongBao);
        txtThongBao = giaoDienThongBao.findViewById(R.id.txtSoLuongThongBao);
        txtThongBao.setText("1");
        giaoDienThongBao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itb = new Intent(MainActivity.this, Notification.class);
                startActivity(itb);
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.itThongBao:
                Intent intent = new Intent(getApplicationContext(), Notification.class);
                startActivity(intent);
            case R.id.action_setting:
//                Intent intent2 = new Intent(getApplicationContext(), TestRealTime.class);
//                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            // Handle the camera action
            Toast.makeText(getApplicationContext(),"Chọn manage",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(getApplicationContext(), Settings.class);
            startActivity(intent2);

        } else if (id == R.id.nav_about) {
            Toast.makeText(getApplicationContext(),"Chọn thông tin",Toast.LENGTH_LONG).show();
            Intent intent2 = new Intent(getApplicationContext(), Information.class);
            startActivity(intent2);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setdulieuserver() {
        Server server = new Server(1,"Label 1","hostnem1.com","Viet Nam","Ubuntu",1,81,51,91,1,1);
        servers.add(server);
        server = new Server(2,"Label 2","hostnem1.com","Chicago","CentOS",1,82,52,92,1,1);
        servers.add(server);
        myref = FirebaseDatabase.getInstance().getReference();
        myref.child("server").setValue(servers);
    }
    private void setdulieucustom() {
        Customer customer = new Customer(1,"admin","admin","admin@gmail.com","0123456789",1);
        customers.add(customer);
        customer = new Customer(1,"admin2","admin2","admin2@gmail.com","0123456789",2);
        customers.add(customer);
        myref = FirebaseDatabase.getInstance().getReference();
        myref.child("info").setValue(customers);
    }
    public static class ServerViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView txtId,txtRam,txtCpu,txtDisk,txtLabel,txtType,txtLocation;

        public ServerViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            txtCpu = itemView.findViewById(R.id.txtCpu);
            txtRam = itemView.findViewById(R.id.txtRam);
            txtDisk = itemView.findViewById(R.id.txtDisk);
            //txtId = itemView.findViewById(R.id.txtId);
            txtLabel = itemView.findViewById(R.id.txtLabel);
            txtType = itemView.findViewById(R.id.txtType);
            txtLocation = itemView.findViewById(R.id.txtLocation);

        }

        public void setId(String id)
        {
            txtId.setText(id+"");
        }
        public void setRam(String id)
        {
            txtRam.setText(id+" %");
        }
        public void setCpu(String id)
        {
            txtCpu.setText(id+" %");
        }
        public void setDisk(String id)
        {
            txtDisk.setText(id+" %");
        }
        public void setLabel(String id)
        {
            txtLabel.setText(id+"");
        }
        public void setType(String id)
        {
            txtType.setText(id+"");
        }
        public void setLoacation(String id)
        {
            txtLocation.setText(id+"");
        }

    }
}
