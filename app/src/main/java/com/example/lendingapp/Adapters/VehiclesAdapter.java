package com.example.lendingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.Model.NewModel;
import com.example.lendingapp.ProductDetails.FullDetailsPage;
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
        Picasso.get().load("https://firebasestorage.googleapis.com/v0/b/lendingapp-fecd8.appspot.com/o/Image%2F1623978806121.jpg?alt=media&token=2027070e-3eaa-4837-a1ea-b8579e8b5258").fit().centerCrop()
                .into(holder.vehicleImage);
        //Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.vehicleImage);
        holder.vehicleName.setText(uploadCurrent.gettitle());
        holder.vehiclePrice.setText(uploadCurrent.getprice());
        holder.vehileLocation.setText(uploadCurrent.getlocation());
        holder.vehileDescription.setText(uploadCurrent.getdescription());

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

            // next page content section
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // starting a new intent here.
                    Intent desc =  new Intent(view.getContext(), FullDetailsPage.class);
                    desc.putExtra("title", String.valueOf(vehicleName.getText()));
                    desc.putExtra("description", String.valueOf(vehileDescription.getText()));
                    desc.putExtra("price", String.valueOf(vehiclePrice.getText()));
                    desc.putExtra("location", String.valueOf(vehileLocation.getText()));
                    //desc.putExtra("Image", String.valueOf(mUploads.get(imageView.getImageAlpha())));
                    //desc.putExtra("image", new String("https://firebasestorage.googleapis.com/v0/b/lendingapp-fecd8.appspot.com/o/Image%2F1623328503213.jpg?alt=media&token=f1242b6c-2a95-43aa-a9ba-9d6413790a11"));
                    view.getContext().startActivity(desc);
                }
            });
            //

            vehicleName=itemView.findViewById(R.id.itemName);
            vehiclePrice=itemView.findViewById(R.id.itemPrice);
            vehileLocation=itemView.findViewById(R.id.itemLocation);
            vehileDescription=itemView.findViewById(R.id.item_description);
            vehicleImage = itemView.findViewById(R.id.productImage);
        }
    }

}
