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
        Picasso.get().load(uploadCurrent.getXimageUrl()).fit().centerCrop()
                .into(holder.electronicImage);
//        Picasso.with(mContext).load(uploadCurrent.getImageUrl()).fit().centerCrop().into(holder.electronicImage);
        holder.electronicName.setText(uploadCurrent.getXtitle());
        holder.electronicPrice.setText(uploadCurrent.getXprice());
        holder.electronicLocation.setText(uploadCurrent.getXlocation());
        holder.electronicDescription.setText(uploadCurrent.getXdescription());

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
