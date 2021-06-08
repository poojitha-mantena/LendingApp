package com.example.lendingapp.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lendingapp.Adapters.ElectronicsAdapter;
import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.PostAdd.AdElectronicsDetails;
import com.example.lendingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Electronics extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    ElectronicsAdapter electronicsAdapter;
    List<NewModel> list;
    SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        getSupportActionBar().setTitle("Electronics");

        FloatingActionButton fab;
        fab = findViewById(R.id.add_electronics_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Electronics.this, AdElectronicsDetails.class);
                startActivity(intent);
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

        recyclerView=(RecyclerView)findViewById(R.id.electronics_recyclerview);
        database= FirebaseDatabase.getInstance().getReference("Electronics");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        electronicsAdapter=new ElectronicsAdapter(this,list);
        recyclerView.setAdapter(electronicsAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    NewModel electronics = dataSnapshot.getValue(NewModel.class);
                    list.add(electronics);
                }
                electronicsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}