package com.example.lendingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>  {

    private Context mContext;
    private List<NewModel> mUploads;

    public BooksAdapter(Context context, List<NewModel> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public BooksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.product_item_list,parent,false);
        return new BooksViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BooksViewHolder holder, int position) {
         NewModel uploadCurrent = mUploads.get(position);
        Glide.with(mContext).load(uploadCurrent.getXimageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getXimageUrl()).into(holder.imageView);
        //Picasso.get().load(String.valueOf(NewModel.class)).into(holder.imageView);
       
        //Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.imageView);
        holder.bookName.setText(uploadCurrent.getXtitle());
        holder.bookPrice.setText(uploadCurrent.getXprice());
        holder.bookLocation.setText(uploadCurrent.getXlocation());
        holder.bookDescription.setText(uploadCurrent.getXdescription());

//        String img_url = uploadCurrent.getXimageUrl();
//        if (!img_url.equalsIgnoreCase(""))
//            Picasso.get().load(img_url)// Place holder image from drawable folder
//                    .resize(110, 110).centerCrop()
//                    .into(holder.imageView);


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
            imageView = itemView.findViewById(R.id.productImage);

        }
    }

}
