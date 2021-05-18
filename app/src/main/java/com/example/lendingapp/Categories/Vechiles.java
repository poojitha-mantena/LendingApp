package com.example.lendingapp.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.lendingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Vechiles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vechiles);
        getSupportActionBar().setTitle("Vehicles");


    }
}