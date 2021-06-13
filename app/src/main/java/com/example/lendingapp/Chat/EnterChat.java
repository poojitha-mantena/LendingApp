package com.example.lendingapp.Chat;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class EnterChat extends AppCompatActivity implements View.OnClickListener {

    private CustomAdapter mAdapter;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private String fromUseridentify;
    private static ArrayList<FriendlyMessage> mFMessages;
    private String currentUser;

    private RecyclerView mRecyclerView;
    private ImageButton msgBtn;

    private EditText msgText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        msgBtn = findViewById(R.id.msgsendbtn);
        msgBtn.setOnClickListener(this);

        msgText = findViewById(R.id.msgedittext);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);

        FirebaseUser user = FireHelper.getInstance().AuthInit().getCurrentUser();
        user.getDisplayName();
        user.getEmail();

        currentUser = subStringName(user.getEmail());

        fromUseridentify = user.getUid();

        mFMessages = new ArrayList<FriendlyMessage>();

        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");

        mHandler = new Handler();
        startRepeatingTask();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }

    private String getTimeStamp() {
        Long tsLong = System.currentTimeMillis() / 1000;
        return tsLong.toString();
    }

    public void updateFetchMessage() {
        fetchMessage();
    }

    private int mInterval = 1000; // 1 seconds by default, can be changed later
    private Handler mHandler;

    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {

                updateFetchMessage();

            } finally {

                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };

    void startRepeatingTask() {
        mStatusChecker.run();
    }

    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
    }


    public void fetchMessage() {

        database.getReference().child("message").addListenerForSingleValueEvent(
                new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        mFMessages = new ArrayList<FriendlyMessage>();

                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                            String fromUserId = ds.child("fromUserId").getValue(String.class);
                            String name = ds.child("name").getValue(String.class);
                            String text = ds.child("text").getValue(String.class);
                            String timestamp = ds.child("timestamp").getValue(String.class);
                            Log.d("TAG", fromUserId + " / " + name + " / " + text + " / " + timestamp);
                            mFMessages.add(new FriendlyMessage(text, name, fromUserId, timestamp));
                        }

                        if (mFMessages.size() > 0) {

                            mAdapter = new CustomAdapter(mFMessages, fromUseridentify);
                            mRecyclerView.setAdapter(mAdapter);
                            mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.w("", "getUser:onCancelled", databaseError.toException());
                    }


                });


    }


    private String subStringName(String str) {

        String[] subString = str.split("@");

        if (subString == null) {
            return "";
        }

        return subString[0];
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.msgsendbtn:

                if (msgText.getText() == null || msgText.getText().length() < 0) return;

                FriendlyMessage friendlyMessage = new FriendlyMessage(msgText.getText().toString().trim(), currentUser, fromUseridentify, getTimeStamp());
                myRef.push().setValue(friendlyMessage).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        // ...
                        Log.w("", " Write was successful!");

                        mFMessages.add(new FriendlyMessage(msgText.getText().toString().trim(), currentUser, fromUseridentify, getTimeStamp()));
                        mAdapter.notifyItemRangeChanged(mFMessages.size(), 1);
                        mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        // ...
                        Log.w("", " Write was failed!");
                    }
                });


                break;


            default:

                break;
        }

    }
}
