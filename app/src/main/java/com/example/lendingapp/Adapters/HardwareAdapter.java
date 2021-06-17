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

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.HardwareViewHolder> {

    private Context mContext;
    private List<NewModel> mUploads;

    public HardwareAdapter(Context context, List<NewModel> uploads){
        mContext = context;
        mUploads = uploads;
    }

    @Override
    public HardwareViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.product_item_list,parent,false);
        return new HardwareViewHolder(v);
    }

    @Override
    public void onBindViewHolder(HardwareAdapter.HardwareViewHolder holder, int position) {
        NewModel uploadCurrent = mUploads.get(position);
        //Glide.with(mContext).load(uploadCurrent.getImageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getImageUrl()).into(holder.imageView);
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lendingapp-fecd8.appspot.com/o/Image%2F1623328503213.jpg?alt=media&token=f1242b6c-2a95-43aa-a9ba-9d6413790a11").fit().centerCrop()
                .into(holder.hardwareImage);
//        Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.hardwareImage);
        holder.hardwareName.setText(uploadCurrent.gettitle());
        holder.hardwarePrice.setText(uploadCurrent.getprice());
        holder.hardwareLocation.setText(uploadCurrent.getlocation());
        holder.hardwareDescription.setText(uploadCurrent.getdescription());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class HardwareViewHolder extends RecyclerView.ViewHolder{

        public TextView hardwareName, hardwarePrice,hardwareLocation,hardwareDescription;
        public ImageView hardwareImage;

        public HardwareViewHolder(View itemView) {
            super(itemView);
            hardwareName=itemView.findViewById(R.id.itemName);
            hardwarePrice=itemView.findViewById(R.id.itemPrice);
            hardwareLocation=itemView.findViewById(R.id.itemLocation);
            hardwareDescription=itemView.findViewById(R.id.item_description);
            hardwareImage = itemView.findViewById(R.id.productImage);
        }
    }
}

