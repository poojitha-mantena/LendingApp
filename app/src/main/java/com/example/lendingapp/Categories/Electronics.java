package com.example.lendingapp.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.lendingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Electronics extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronics);
        getSupportActionBar().setTitle("Electronics");

    }
}