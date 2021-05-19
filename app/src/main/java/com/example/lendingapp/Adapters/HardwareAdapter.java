package com.example.lendingapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.Model.Model;
import com.example.lendingapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HardwareAdapter extends RecyclerView.Adapter<HardwareAdapter.HardwareViewHolder> {

    private Context mContext;
    private List<Model> mUploads;

    public HardwareAdapter(Context context, List<Model> uploads){
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
        Model uploadCurrent = mUploads.get(position);
        //Glide.with(mContext).load(uploadCurrent.getImageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getImageUrl()).into(holder.imageView);
        Picasso.get().load(uploadCurrent.getImageUrl()).fit().centerCrop()
                .into(holder.hardwareImage);
        holder.hardwareName.setText(uploadCurrent.getMtitle());
        holder.hardwarePrice.setText(uploadCurrent.getMprice());
        holder.hardwareLocation.setText(uploadCurrent.getMlocation());
        holder.hardwareDescription.setText(uploadCurrent.getMdescription());
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

