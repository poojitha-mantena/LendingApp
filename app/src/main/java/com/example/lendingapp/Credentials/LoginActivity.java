package com.example.lendingapp.Credentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lendingapp.Dashboard.SelectCategoryPage;
import com.example.lendingapp.ForgotPassword;
import com.example.lendingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView register,forgotPassword;
    private EditText editTextEmail,editTextPassword;
    private Button login;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        register = (TextView) findViewById(R.id.tvSignUp);
        register.setOnClickListener(this);

        login=(Button)findViewById(R.id.LoginButton);
        login.setOnClickListener(this);

        editTextEmail=(EditText)findViewById(R.id.etEmail);
        editTextPassword=(EditText)findViewById(R.id.etPassword);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        mAuth=FirebaseAuth.getInstance();

        forgotPassword=(TextView)findViewById(R.id.tvForgotPassword);
        forgotPassword.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tvSignUp:
                startActivity(new Intent(this,SignUpActivity.class));
                break;

            case R.id.LoginButton:
                userLogin();
                break;

            case R.id.tvForgotPassword:
                    startActivity(new Intent(this, ForgotPassword.class));
                    break;
        }
    }
    private void userLogin() {
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();

        if(email.isEmpty()){
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid Email!");
            editTextEmail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }
        if(password.length() < 6) {
            editTextPassword.setError("Minimum password length is 6 characters!");
            editTextPassword.requestFocus();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

                    if(user.isEmailVerified()) {
                        startActivity(new Intent(LoginActivity.this, SelectCategoryPage.class));
                    }  else {
                            user.sendEmailVerification();
                            Toast.makeText(LoginActivity.this,"Check Your Email To Verify Your Account",Toast.LENGTH_LONG).show();
                        }
                }else{
                    Toast.makeText(LoginActivity.this,"Failed to Login, Please try again!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}