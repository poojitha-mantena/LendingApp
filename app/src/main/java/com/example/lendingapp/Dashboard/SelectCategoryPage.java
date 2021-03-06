package com.example.lendingapp.Dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.example.lendingapp.Categories.Books;
import com.example.lendingapp.Categories.Electronics;
import com.example.lendingapp.Categories.Hardware;
import com.example.lendingapp.Categories.Vechiles;
import com.example.lendingapp.Chat.StartActivity;
import com.example.lendingapp.CreateProfile;
import com.example.lendingapp.Credentials.LoginActivity;
import com.example.lendingapp.R;
import com.google.firebase.auth.FirebaseAuth;

public class SelectCategoryPage extends AppCompatActivity {


    private CardView VechilesCard,BooksCard,ElectronicsCard,HardwareCard;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_page);
        //getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();

        VechilesCard = (CardView) findViewById(R.id.vechileCard);
        BooksCard = (CardView) findViewById(R.id.booksCard);
        ElectronicsCard = (CardView) findViewById(R.id.electronicsCard);
        HardwareCard = (CardView) findViewById(R.id.hardwareCard);


        VechilesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCategoryPage.this, Vechiles.class);
                startActivity(intent);
            }
        });

        BooksCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCategoryPage.this, Books.class);
                startActivity(intent);
            }
        });

        ElectronicsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCategoryPage.this, Electronics.class);
                startActivity(intent);
            }
        });

        HardwareCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCategoryPage.this, Hardware.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawermenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        if(item.getItemId()==R.id.myads){
//            Toast.makeText(this, "You clicked my adds", Toast.LENGTH_SHORT).show();
//        }
        if(item.getItemId()==R.id.chat){
            Intent i=new Intent(SelectCategoryPage.this, StartActivity.class);
            startActivity(i);
        }
//        else if(item.getItemId()==R.id.cprofile){
//            Intent i=new Intent(SelectCategoryPage.this, CreateProfile.class);
//            startActivity(i);
//        }
        else if(item.getItemId()==R.id.logout){
            mAuth.signOut();
            signOut();
        }
        else{
            return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void signOut() {
        Intent intent = new Intent(SelectCategoryPage.this,LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}