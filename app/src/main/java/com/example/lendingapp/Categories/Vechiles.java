package com.example.lendingapp.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.lendingapp.Chat.ChatPage;
import com.example.lendingapp.Chat.CustomAdapter;
import com.example.lendingapp.Chat.StartActivity;
import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.PostAdd.AdVehicleDetails;
import com.example.lendingapp.R;
import com.example.lendingapp.Adapters.VehiclesAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Vechiles extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    VehiclesAdapter vehiclesAdapter;
    List<NewModel> list;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechiles);
        getSupportActionBar().setTitle("Vehicles");

        FloatingActionButton fab;
        fab = findViewById(R.id.add_vechiles_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Vechiles.this, AdVehicleDetails.class);
                startActivity(intent);

        FloatingActionButton chat;
        chat = findViewById(R.id.chat_button);
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Vechiles.this, StartActivity.class);
                startActivity(intent);
            }
        });

            }

        });



        // ignore this portion for now its not complete

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // repeated the code here to get datas from database.
//                database.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                            Model books = dataSnapshot.getValue(Model.class);
//                            list.add(books);
//                        }
//                        booksAdapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError error) {
//
//                    }
//                });
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // ignore until this point.

        recyclerView=(RecyclerView)findViewById(R.id.vehicles_recyclerview);
        database= FirebaseDatabase.getInstance().getReference("Vehicle");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        vehiclesAdapter=new VehiclesAdapter(this,list);
        recyclerView.setAdapter(vehiclesAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    NewModel vehicles = dataSnapshot.getValue(NewModel.class);
                    list.add(vehicles);
                }
                vehiclesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}