package com.example.lendingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class CreateProfile extends Activity {
    EditText etName,etPhone,etMail;
    ImageView profileImage;
    TextView profile;
    private String email;
    private FirebaseDatabase database;
    private DatabaseReference userRef;
    private static final String User="Users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //profile = findViewById(R.id.TVprofile);
        profileImage=findViewById(R.id.iv_profile);
        etName=findViewById(R.id.et_profileName);
        etPhone=findViewById(R.id.et_profileNum);
        etMail=findViewById(R.id.et_profileMail);

        database=FirebaseDatabase.getInstance();
        userRef=database.getReference(User);

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()){
                    if (ds.child("email").getValue().equals(email)){
                        etName.setText(ds.child("fullname").getValue(String.class));
                        etPhone.setText(ds.child("phone").getValue(String.class));
                        etMail.setText(email);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
    }
}
