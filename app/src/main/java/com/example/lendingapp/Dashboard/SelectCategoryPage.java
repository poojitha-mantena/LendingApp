package com.example.lendingapp.Dashboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.lendingapp.Categories.Books;
import com.example.lendingapp.Categories.Electronics;
import com.example.lendingapp.Categories.Hardware;
import com.example.lendingapp.Categories.Vechiles;
import com.example.lendingapp.R;

public class SelectCategoryPage extends AppCompatActivity {


    private CardView VechilesCard,BooksCard,ElectronicsCard,HardwareCard;
    private DrawerLayout mDrawerlayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_page);
        //getSupportActionBar().hide();

        mDrawerlayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(SelectCategoryPage.this,mDrawerlayout,R.string.open,R.string.close);
        mDrawerlayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
    public boolean onOptionsItemSelected(MenuItem item){
        if (mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}