package com.example.lendingapp.Categories;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Adapter;

import com.example.lendingapp.BooksAdapter;
import com.example.lendingapp.Model.Model;
import com.example.lendingapp.ModelBooks;
import com.example.lendingapp.PostAdd.AdBookDetails;
import com.example.lendingapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
    List<ModelBooks> list;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        getSupportActionBar().setTitle("Books");

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
                    ModelBooks books=dataSnapshot.getValue(ModelBooks.class);
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