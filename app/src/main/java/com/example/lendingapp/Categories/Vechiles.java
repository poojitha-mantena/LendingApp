package com.example.lendingapp.Categories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lendingapp.PostAdd.AdVehicleDetails;
import com.example.lendingapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Vechiles extends AppCompatActivity {

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
            }
        });
    }
}