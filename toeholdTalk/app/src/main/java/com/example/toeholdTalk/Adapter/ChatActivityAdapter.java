package com.example.toeholdTalk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Activity.FriendProfileActivity;
import com.example.toeholdTalk.Model.ChatContent;
import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.R;
import com.example.toeholdTalk.Util.HandleTime;
import com.squareup.picasso.Picasso;

import java.security.AccessControlContext;
import java.util.ArrayList;
public class ChatActivityAdapter extends RecyclerView.Adapter<ChatActivityAdapter.ViewHolder> {
    private Context context;
    private ArrayList<ChatContent> items;
    private String imageUrl;
    private static final int CHAT_LEFT=0;
    private static final int CHAT_RIGHT=1;

    public ChatActivityAdapter(Context context, ArrayList<ChatContent> items, String imageUrl) {
        this.context = context;
        this.items= items;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public ChatActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            // viewType이 0이면 내 채팅 레이아웃을 담은 holder를 리턴
            case CHAT_RIGHT:
                View myItemView = inflater.inflate(R.layout.my_chat_item, parent, false);
                return new ViewHolder(myItemView);
            // viewType이 1이면 친구 채팅 레이아웃을 담은 holder를 리턴
            case CHAT_LEFT:
                View partnerItemView = inflater.inflate(R.layout.partner_chat_item, parent, false);
                return new ViewHolder(partnerItemView);
            default:
                return null;
        }
    }


    @Override
    public void onBindViewHolder(@NonNull ChatActivityAdapter.ViewHolder holder, int position) {
        ChatContent item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView chatContent;
        TextView chatTime;
        ImageView partnerImage;
        TextView partnerName;
        TextView partnerChatContent;
        TextView partnerChatTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myName);
            chatContent = itemView.findViewById(R.id.ChatContent);
            chatTime = itemView.findViewById(R.id.ChatTime);
            partnerImage = itemView.findViewById(R.id.partnerImage);
            partnerName = itemView.findViewById(R.id.partnerName);
            partnerChatContent = itemView.findViewById(R.id.partnerChatContent);
            partnerChatTime = itemView.findViewById(R.id.partnerChatTime);
        }

        public void setItem(final ChatContent item) {
            if(item.getReceiver().equals(MyInfo.getMyId())){
                partnerName.setText(item.getSender());
                partnerChatContent.setText(item.getMessage());
                partnerChatTime.setText(HandleTime.toSimpleFormat(item.getTime()));
                if(!imageUrl.equals("")) Picasso.get().load("http://45.32.38.196:5000/"+ imageUrl).fit().into(partnerImage);
            }
            else{
                name.setText(item.getReceiver());
                chatContent.setText(item.getMessage());
                chatTime.setText(HandleTime.toSimpleFormat(item.getTime()));
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(items.get(position).getSender().equals(MyInfo.getMyId())) return CHAT_RIGHT;
        else return CHAT_LEFT;
    }
}
