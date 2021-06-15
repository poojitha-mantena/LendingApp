package com.example.lendingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.ProductDetails.FullDetailsPage;
import com.example.lendingapp.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BooksViewHolder>  {

    private Context mContext;
    private List<NewModel> mUploads;
    StorageReference storageReference= FirebaseStorage.getInstance().getReference("Image");

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
        //Glide.with(mContext).load(uploadCurrent.getXimageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getimage()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getXimageUrl()).into(holder.imageView);
        //Picasso.get().load(String.valueOf(NewModel.class)).into(holder.imageView);
      //https://firebasestorage.googleapis.com/v0/b/lendingapp-fecd8.appspot.com/o/Image%2F1623287634999.jpg?alt=media&token=69d10de0-91e6-4bf9-a8a8-c50451b5fd5d
        //Picasso.get().load(storage.get).fit().centerCrop().into(holder.imageView);
        holder.bookName.setText(uploadCurrent.gettitle());
        holder.bookPrice.setText(uploadCurrent.getprice());
        holder.bookLocation.setText(uploadCurrent.getlocation());
        holder.bookDescription.setText(uploadCurrent.getdescription());


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
            // next page content section
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // starting a new intent here.
                    Intent desc =  new Intent(view.getContext(), FullDetailsPage.class);
                    desc.putExtra("title", String.valueOf(bookName.getText()));
                    desc.putExtra("description", String.valueOf(bookDescription.getText()));
                    desc.putExtra("price", String.valueOf(bookPrice.getText()));
                    desc.putExtra("location", String.valueOf(bookLocation.getText()));
                    //desc.putExtra("Image", String.valueOf(mUploads.get(imageView.getImageAlpha())));
                    desc.putExtra("image", String.valueOf(imageView));
                    view.getContext().startActivity(desc);
                }
            });
            //
            bookName=itemView.findViewById(R.id.itemName);
            bookPrice=itemView.findViewById(R.id.itemPrice);
            bookLocation=itemView.findViewById(R.id.itemLocation);
            bookDescription=itemView.findViewById(R.id.item_description);
            imageView = itemView.findViewById(R.id.productImage);

        }
    }

}
