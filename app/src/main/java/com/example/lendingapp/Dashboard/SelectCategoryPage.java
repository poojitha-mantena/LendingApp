package com.example.lendingapp.Dashboard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.lendingapp.Navigation.NavigationBar;
import com.example.lendingapp.R;

public class SelectCategoryPage extends AppCompatActivity {

    private CardView card1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category_page);

        card1 = (CardView) findViewById(R.id.vechileCard);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectCategoryPage.this, NavigationBar.class);
                startActivity(intent);
            }
        });
    }
}