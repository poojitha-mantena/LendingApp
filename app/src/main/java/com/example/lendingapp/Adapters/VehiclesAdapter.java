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

public class VehiclesAdapter extends RecyclerView.Adapter<VehiclesAdapter.vehiclesViewHolder> {

    private Context mContext;
    private List<NewModel> mUploads;

    public VehiclesAdapter(Context context, List<NewModel> uploads){
        mContext = context;
        mUploads = uploads;
    }


    @Override
    public vehiclesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.product_item_list,parent,false);
        return new vehiclesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(VehiclesAdapter.vehiclesViewHolder holder, int position) {

        NewModel uploadCurrent = mUploads.get(position);
        //Glide.with(mContext).load(uploadCurrent.getImageUrl()).into(holder.imageView);
        //Glide.with(mContext).load(mUploads.get(position).getImageUrl()).into(holder.imageView);
        //Picasso.get().load(uploadCurrent.getImageUrl()).into(holder.imageView);
        Picasso.get().load(uploadCurrent.getXimageUrl()).fit().centerCrop()
                .into(holder.vehicleImage);
        //Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.vehicleImage);
        holder.vehicleName.setText(uploadCurrent.getXtitle());
        holder.vehiclePrice.setText(uploadCurrent.getXprice());
        holder.vehileLocation.setText(uploadCurrent.getXlocation());
        holder.vehileDescription.setText(uploadCurrent.getXdescription());

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class vehiclesViewHolder extends RecyclerView.ViewHolder{

        public TextView vehicleName, vehiclePrice,vehileLocation,vehileDescription;
        public ImageView vehicleImage;

        public vehiclesViewHolder(View itemView) {
            super(itemView);

            vehicleName=itemView.findViewById(R.id.itemName);
            vehiclePrice=itemView.findViewById(R.id.itemPrice);
            vehileLocation=itemView.findViewById(R.id.itemLocation);
            vehileDescription=itemView.findViewById(R.id.item_description);
            vehicleImage = itemView.findViewById(R.id.productImage);
        }
    }

}
