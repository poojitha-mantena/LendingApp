package com.example.lendingapp.Chat;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lendingapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private ArrayList<Message> listItem;
    private ArrayList<FriendlyMessage> listItem;
    private Context context;

    private static final int VIEW_HOLDER_ME = 0;
    private static final int VIEW_HOLDER_YOU = 1;

    private String mfromUserId;


    public CustomAdapter(ArrayList<FriendlyMessage> list, String fromUserId) {
        this.listItem = list;
        this.mfromUserId = fromUserId;
    }

    @Override
    public int getItemViewType(int position) {
        if (listItem.get(position).getFromUserId().equalsIgnoreCase(mfromUserId)) {
            return VIEW_HOLDER_ME;
        } else {
            return VIEW_HOLDER_YOU;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        switch (viewType) {
            case VIEW_HOLDER_ME:
                return new ViewHolderMe(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_message, viewGroup, false));
            case VIEW_HOLDER_YOU:
                return new ViewHolderYou(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.their_message, viewGroup, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder v, int pos) {
        if (v instanceof ViewHolderMe) { // Handle Image Layout
            ViewHolderMe viewHolderImage = (ViewHolderMe) v;
            viewHolderImage.messageBody.setText(String.format("%s", listItem.get(pos).getText().trim()));
            viewHolderImage.itemView.setTag(viewHolderImage);
        } else if (v instanceof ViewHolderYou) { // Handle Video Layout
            ViewHolderYou viewHolderYou = (ViewHolderYou) v;
            viewHolderYou.name.setText(String.format("%s", listItem.get(pos).getName()));
            viewHolderYou.messageBody.setText(String.format("%s", listItem.get(pos).getText()));

            GradientDrawable drawable = (GradientDrawable) viewHolderYou.avatar.getBackground();
            drawable.setColor(Color.GRAY);
            viewHolderYou.itemView.setTag(viewHolderYou);
        }
    }

    public class ViewHolderMe extends RecyclerView.ViewHolder {

        public TextView messageBody;

        public ViewHolderMe(final View itemView) {
            super(itemView);
            messageBody = (TextView) itemView.findViewById(R.id.message_body);
        }
    }

    public class ViewHolderYou extends RecyclerView.ViewHolder {

        public View avatar;
        public TextView name;
        public TextView messageBody;

        public ViewHolderYou(final View itemView) {
            super(itemView);
            avatar = (View) itemView.findViewById(R.id.avatar);
            name = (TextView) itemView.findViewById(R.id.name);
            messageBody = (TextView) itemView.findViewById(R.id.message_body);
        }
    }





    @Override
    public int getItemCount() {
        return (null != listItem ? listItem.size() : 0);
    }
}
