package com.example.lendingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ElectronicsAdapter extends RecyclerView.Adapter<ElectronicsAdapter.ElectronicsViewHolder> {

    private Context mContext;
    private List<NewModel> mUploads;

    public ElectronicsAdapter(Context context, List<NewModel> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public ElectronicsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.product_item_list,parent,false);
        return new ElectronicsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ElectronicsAdapter.ElectronicsViewHolder holder, int position) {
        NewModel uploadCurrent = mUploads.get(position);
        //Glide.with(mContext).load(uploadCurrent.getImageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getImageUrl()).into(holder.imageView);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lendingapp-fecd8.appspot.com/o/Image%2F1623328503213.jpg?alt=media&token=f1242b6c-2a95-43aa-a9ba-9d6413790a11").fit().centerCrop()
                .into(holder.electronicImage);
        holder.electronicName.setText(uploadCurrent.gettitle());
        holder.electronicPrice.setText(uploadCurrent.getprice());
        holder.electronicLocation.setText(uploadCurrent.getlocation());
        holder.electronicDescription.setText(uploadCurrent.getdescription());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ElectronicsViewHolder extends RecyclerView.ViewHolder{

        public TextView electronicName, electronicPrice,electronicLocation,electronicDescription;
        public ImageView electronicImage;

        public ElectronicsViewHolder(View itemView) {
            super(itemView);
            electronicName=itemView.findViewById(R.id.itemName);
            electronicPrice=itemView.findViewById(R.id.itemPrice);
            electronicLocation=itemView.findViewById(R.id.itemLocation);
            electronicDescription=itemView.findViewById(R.id.item_description);
            electronicImage = itemView.findViewById(R.id.productImage);
        }
    }
}
