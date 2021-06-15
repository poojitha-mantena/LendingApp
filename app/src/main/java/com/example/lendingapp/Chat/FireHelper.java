package com.example.lendingapp.Chat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FireHelper {

    private static FireHelper single_instance = null;


    public String s;
    private static FirebaseAuth mAuth;
    private static FirebaseUser mUser;

    private FireHelper()
    {
        s = "Hello I am a string part of Singleton class";
    }

    // static method to create instance of Singleton class
    public static FireHelper getInstance()
    {
        if (single_instance == null)
            single_instance = new FireHelper();

        return single_instance;
    }

    public static FirebaseAuth AuthInit() {

        mAuth = FirebaseAuth.getInstance();

        return mAuth;

    }

    public static FirebaseUser getCurrentUser() {

        mUser = FirebaseAuth.getInstance().getCurrentUser();

        return mUser;

    }



}
