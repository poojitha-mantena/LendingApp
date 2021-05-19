package com.example.lendingapp.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lendingapp.Adapters.BooksAdapter;
import com.example.lendingapp.Model.Model;
import com.example.lendingapp.PostAdd.AdBookDetails;
import com.example.lendingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Books extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference database;
    BooksAdapter booksAdapter;
    List<Model> list;
    SwipeRefreshLayout swipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        getSupportActionBar().setTitle("Books");

        FloatingActionButton fab;
        fab = findViewById(R.id.add_books_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Books.this, AdBookDetails.class);
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

        recyclerView=(RecyclerView)findViewById(R.id.books_recyclerview);
        database=FirebaseDatabase.getInstance().getReference("Books");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list=new ArrayList<>();
        booksAdapter=new BooksAdapter(this,list);
        recyclerView.setAdapter(booksAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model books = dataSnapshot.getValue(Model.class);
                    list.add(books);
                }
                booksAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    
}