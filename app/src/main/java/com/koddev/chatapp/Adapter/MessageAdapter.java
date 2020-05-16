package com.koddev.chatapp.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.koddev.chatapp.Model.Chat;
import com.koddev.chatapp.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    public static  final int MSG_TYPE_TEXT_LEFT = 0;
    public static  final int MSG_TYPE_TEXT_RIGHT = 1;
    public static  final int MSG_TYPE_IMAGE_LEFT = 2;
    public static  final int MSG_TYPE_IMAGE_RIGHT = 3;

    private Context mContext;
    private List<Chat> mChat;
    private String imageurl;

    FirebaseUser fuser;

    private FirebaseAuth mAuth;
    private DatabaseReference UsersDataBaseReference;

    public MessageAdapter(Context mContext, List<Chat> mChat, String imageurl){
        this.mChat = mChat;
        this.mContext = mContext;
        this.imageurl = imageurl;
    }

    @NonNull
    @Override
    public MessageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_TEXT_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_right, parent, false);
            return new MessageAdapter.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chat_item_left, parent, false);
            return new MessageAdapter.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Chat chat = mChat.get(position);
        String fromMessageType = chat.getMessageType();
        RelativeLayout.LayoutParams params= new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        if(fromMessageType.equals("text"))
        {
            holder.messagePicture.setVisibility(View.INVISIBLE);
            holder.show_message.setText(chat.getMessage());
            params.addRule(RelativeLayout.BELOW, R.id.show_message);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.txt_seen.setLayoutParams(params);

        }
        else
        {
            holder.show_message.setVisibility(View.INVISIBLE);
            holder.show_message.setPadding(0,0,0,0);

            params.addRule(RelativeLayout.BELOW, R.id.message_image_view);
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.txt_seen.setLayoutParams(params);

            Glide.with(mContext).load(chat.getMessage()).into(holder.messagePicture);
        }
        if (imageurl.equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(mContext).load(imageurl).into(holder.profile_image);
        }

        if (position == mChat.size()-1){
            if (chat.isIsseen()){
                holder.txt_seen.setText("Seen");
            } else {
                holder.txt_seen.setText("Delivered");
            }
        } else {
            holder.txt_seen.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return mChat.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView show_message;
        public ImageView profile_image;
        public TextView txt_seen;
        public ImageView messagePicture;

        public ViewHolder(View itemView) {
            super(itemView);

            show_message = itemView.findViewById(R.id.show_message);
            profile_image = itemView.findViewById(R.id.profile_image);
            txt_seen = itemView.findViewById(R.id.txt_seen);
            messagePicture = (ImageView)itemView.findViewById(R.id.message_image_view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mChat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_TEXT_RIGHT;
        } else {
            return MSG_TYPE_TEXT_LEFT;
        }
    }
}