package com.example.lendingapp;

import android.content.Context;
import android.media.Image;
import android.text.style.UpdateLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.Model.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>  {

    private Context mContext;
    private List<ModelBooks> mUploads;

    public BooksAdapter(Context context, List<ModelBooks> uploads){
        mContext=context;
        mUploads=uploads;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.product_item_list,parent,false);
        return new BooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
         ModelBooks uploadCurrent = mUploads.get(position);
        holder.bookName.setText(uploadCurrent.getBook_name());
        holder.bookPrice.setText(uploadCurrent.getBook_price());
        holder.bookLocation.setText(uploadCurrent.getBook_description());
        holder.bookDescription.setText(uploadCurrent.getBook_description());


    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class BooksViewHolder extends RecyclerView.ViewHolder{

        public TextView bookName, bookPrice,bookLocation,bookDescription;
        public ImageView imageView;

        public BooksViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName=itemView.findViewById(R.id.itemName);
            bookPrice=itemView.findViewById(R.id.itemPrice);
            bookLocation=itemView.findViewById(R.id.itemLocation);
            bookDescription=itemView.findViewById(R.id.item_description);
            imageView=itemView.findViewById(R.id.imageView);

        }
    }

}