package com.example.lendingapp.ProductDetails;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lendingapp.Chat.StartActivity;
import com.example.lendingapp.R;

public class FullDetailsPage extends AppCompatActivity {

    TextView itemTitle, itemPrice, itemDescription, itemLocation;
    ImageView chatOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_details_page);

        itemTitle = findViewById(R.id.tvTitle);
        itemPrice = findViewById(R.id.tvPrice);
        itemDescription = findViewById(R.id.tvDescription);
        itemLocation = findViewById(R.id.tvPlace);
        chatOption = findViewById(R.id.chat_option);
        //productImage = findViewById(R.id.ImageOfProduct);
        Intent desc = getIntent();
        String title = desc.getStringExtra("title");
        String description = desc.getStringExtra("description");
        String price = desc.getStringExtra("price");
        String location = desc.getStringExtra("location");
        String image = desc.getStringExtra("image");
        itemTitle.setText(title);
        itemPrice.setText(price);
        itemDescription.setText(description);
        itemLocation.setText(location);
        //productImage.setImageURI(Uri.parse(image));
        //productImage.setImageDrawable(Drawable.createFromPath(image));
        //productImage.setImageURI(Uri.parse(image));

        chatOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent chat = new Intent(view.getContext(), StartActivity.class);
                view.getContext().startActivity(chat);
            }
        });
    }
}