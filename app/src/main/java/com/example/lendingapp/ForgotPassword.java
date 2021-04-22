package com.example.lendingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText emailET;
    private Button resetPass;
    private ProgressBar progressBar;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        emailET = (EditText) findViewById(R.id.etEmail);
        resetPass = (Button) findViewById(R.id.ResetButton);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        auth = FirebaseAuth.getInstance();

        resetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String etEmail=emailET.getText().toString().trim();

        if(etEmail.isEmpty()){
            emailET.setError("Email Is Required!");
            emailET.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(etEmail).matches()){
            emailET.setError("Provide A Valid Email!");
            emailET.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(etEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(ForgotPassword.this,"Check Your Email To Reset Password",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ForgotPassword.this,"Some Error Has Occured",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}