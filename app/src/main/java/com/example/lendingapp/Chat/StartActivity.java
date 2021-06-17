package com.example.lendingapp.Chat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lendingapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private ArrayList<Message> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;

    private HashMap<String, String> dict1;
    private HashMap<String, String> dict2;
    private HashMap<String, String> dict3;
    private HashMap<String, String> dict4;
    private HashMap<String, String> dict5;

    private ArrayList<HashMap<String, String>> arrMap;

    private Button startBtn;
    private Button disBtn;

    private FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        disBtn = findViewById(R.id.disbutton);
        disBtn.setOnClickListener(this);


        startBtn = findViewById(R.id.startbutton);
        startBtn.setOnClickListener(this);
        initializeMapData();
        mAuth = FireHelper.getInstance().AuthInit();

    }

    public void initializeMapData() {

        arrMap =  new ArrayList<HashMap<String, String>>();

        dict1 = new HashMap<>();
        dict1.put("name", "Suhail");
        dict1.put("email", "suhailmohd25@gmail.com");
        dict1.put("uid", "f0CiREe6kWP8s3PXkiGhgeBfm433");

        arrMap.add(0, dict1);

        dict2 = new HashMap<>();
        dict2.put("name", "Bornak");
        dict2.put("email", "bornakpaul@gmail.com");
        dict2.put("uid", "cNDViQ0QmBXHq2DRoZp87mqB4nc2");

        arrMap.add(1, dict2);

        dict3 = new HashMap<>();
        dict3.put("name", "Poojitha");
        dict3.put("email", "poojithamantena1@gmail.com");
        dict3.put("uid", "5Bm44MEExtXxDSZFJjSXZNj25KX2");

        arrMap.add(2, dict3);

        dict4 = new HashMap<>();
        dict4.put("name", "Hitesh");
        dict4.put("email", "hitucool10@gmail.com");
        dict4.put("uid", "kIyW7hkzynRxNdHUpiYOJvm4xGI2");

        arrMap.add(3, dict4);

        dict5 = new HashMap<>();
        dict5.put("name", "Rimo");
        dict5.put("email", "rimolmg@gmail.com");
        dict5.put("uid", "JeaHfKbPUmXadkrTIW09tCl4Bjt1");


        arrMap.add(4, dict5);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.disbutton:

                AlertDialog.Builder builderSingle = new AlertDialog.Builder(StartActivity.this);
//                builderSingle.setIcon(R.drawable.ic_send_black_24dp);
                builderSingle.setTitle("Select!!..");

                final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(StartActivity.this, android.R.layout.select_dialog_singlechoice);
                arrayAdapter.add("Suhail");
                arrayAdapter.add("Bornak");
                arrayAdapter.add("Poojitha");
                arrayAdapter.add("Hitesh");
                arrayAdapter.add("Rimo");

                builderSingle.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builderSingle.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String strName = arrayAdapter.getItem(which);

                        HashMap<String, String> mapSelect = arrMap.get(which);

                        String str = mapSelect.get("email").trim();
                        String email = mapSelect.get("email").trim();

                        disBtn.setText(str);

                        signIn(email, "123456");

                    }
                });
                builderSingle.show();

                break;

            case R.id.startbutton:


                break;

            default:

                break;
        }

    }

    private String subStringName (String str) {

        String[] subString = str.split("@");

        if(subString == null) {
            return "";
        }

        return subString[0];
    }


    private void signIn(final String email, String password) {
        Log.d("", "signIn:" + email);

        // [START sign_in_with_email]
        Task<AuthResult> authResultTask = mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("", "signInWithEmail:success");


                            // Write a message to the database
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference myRef = database.getReference("chat");

                            FChat friendlyMessage = new FChat(email, subStringName(email), mAuth.getCurrentUser().getUid());
                            myRef.push().setValue(friendlyMessage);


                            startActivity(new Intent(StartActivity.this, EnterChat.class));
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("", "signInWithEmail:failure", task.getException());
                            Toast.makeText(StartActivity.this, "   " + task.getException() +"   Authentication failed.", Toast.LENGTH_SHORT).show();

                        }

                        // [START_EXCLUDE]
                        if (!task.isSuccessful()) {

                        }

                        // [END_EXCLUDE]
                    }
                });


        // [END sign_in_with_email]
    }

}