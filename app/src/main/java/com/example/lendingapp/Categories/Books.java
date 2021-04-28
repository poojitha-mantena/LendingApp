package com.example.lendingapp.Categories;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;

import com.example.lendingapp.BooksAdapter;
import com.example.lendingapp.Model.Model;
import com.example.lendingapp.ModelBooks;
import com.example.lendingapp.PostAdd.AdBookDetails;
import com.example.lendingapp.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class Books extends AppCompatActivity {

    RecyclerView recyclerView;
    BooksAdapter booksAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        getSupportActionBar().setTitle("Books");

        recyclerView=(RecyclerView)findViewById(R.id.books_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ModelBooks> options = new FirebaseRecyclerOptions.Builder<ModelBooks>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Books"),ModelBooks.class)
                .build();
        
    }

    
}