package com.example.lendingapp.Credentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.UserHandle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lendingapp.PostAdd.AdBookDetails;
import com.example.lendingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import userObject.User;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView loginhere;
    private Button registerUser;
    private EditText editTextFullName, editTextEmail, editTextPassword, editTextPhoneNumber;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();


        loginhere = (TextView) findViewById(R.id.tvSignIn);
        loginhere.setOnClickListener(this);

        registerUser = (Button) findViewById(R.id.RegisterUser);
        registerUser.setOnClickListener(this);

        editTextFullName = (EditText)findViewById(R.id.etFullname);
        editTextEmail = (EditText)findViewById(R.id.etEmail);
        editTextPassword = (EditText)findViewById(R.id.etPassword);
        editTextPhoneNumber = (EditText)findViewById(R.id.etPhone);


        progressBar = (ProgressBar) findViewById(R.id.progressbar);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tvSignIn:
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.RegisterUser:
                registerUser();
                break;
        }
    }

    private void registerUser(){
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String fullname = editTextFullName.getText().toString().trim();
        String phonenumber = editTextPhoneNumber.getText().toString().trim();


        if(fullname.isEmpty()){
            editTextFullName.setError("Please enter your full name");
            editTextFullName.requestFocus();
            return;
        }
        if(phonenumber.isEmpty()){
            editTextPhoneNumber.setError("Please enter phone number");
            editTextPhoneNumber.requestFocus();
            return;

        }
        if(phonenumber.length() < 10) {
            editTextPhoneNumber.setError("Please enter a valid 10 digit number");
            editTextPhoneNumber.requestFocus();
            return;
        }

        if(email.isEmpty()){
            editTextEmail.setError("Please enter your email");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please provide a valid email address.");
            editTextEmail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            editTextPassword.setError("Password cannot be empty");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6){
            editTextPassword.setError("Password should be more than 5 characters.");
            editTextPassword.requestFocus();
            return;
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(fullname, phonenumber, email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Toast.makeText(SignUpActivity.this, "User has been registered successfully",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);//REDIRECT TO LOGIN PAGE
                                    }else {
                                        Toast.makeText(SignUpActivity.this,"Failed to register! Try again.",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.VISIBLE);
                                    }
                                }
                            });

                        }else {
                            Toast.makeText(SignUpActivity.this,"Failed to register",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    }
                });
    }
}