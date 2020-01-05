package com.example.toeholdTalk;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ViewHolder> {

    private SharedPreferences preferences;
    private Context context;
    ArrayList<ChatContent> items;

    /*
    public ChatRoomAdapter(ArrayList<ChatContent> chatContents) {
        items = chatContents;
    }

     */

    @NonNull
    @Override
    public ChatRoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.my_chat_item, parent, false);
        return new ViewHolder(itemView);

/*
        switch (viewType) {
            // viewType이 0이면 내 채팅 레이아웃을 담은 holder를 리턴
            case 0:
                View myItemView = inflater.inflate(R.layout.my_chat_item, parent, false);
                return new ViewHolder(myItemView);
            // viewType이 1이면 친구 채팅 레이아웃을 담은 holder를 리턴
            case 1:
                View partnerItemView = inflater.inflate(R.layout.partner_chat_item, parent, false);
                return new ViewHolder(partnerItemView);
            default:
                return null;
        }

 */
    }



    @Override
    public void onBindViewHolder(@NonNull ChatRoomAdapter.ViewHolder holder, int position) {
        ChatContent item = items.get(position);
        holder.name.setText(item.getName());
        holder.chatContent.setText(item.getChat());
        holder.chatTime.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView chatContent;
        TextView chatTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.myName);
            chatContent = itemView.findViewById(R.id.chatContent);
            chatTime = itemView.findViewById(R.id.chatTime);
        }
    }

    /*
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView myName;
        TextView chatContent;
        TextView chatTime;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myName = itemView.findViewById(R.id.myName);
            chatContent = itemView.findViewById(R.id.chatContent);
            chatTime = itemView.findViewById(R.id.chatTime);
        }
    }
    public static class PartnerViewHolder extends RecyclerView.ViewHolder {
        ImageView partnerImage;
        TextView partnerName;
        TextView chatContent;
        TextView chatTime;
        public PartnerViewHolder(@NonNull View itemView) {
            super(itemView);
            partnerImage = itemView.findViewById(R.id.partnerImage);
            partnerName = itemView.findViewById(R.id.partnerName);
            chatContent = itemView.findViewById(R.id.chatContent);
            chatTime = itemView.findViewById(R.id.chatTime);
        }
    }


    @Override
    public int getItemViewType(int position) {
        preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);
        return who(preferences, position)? 0: 1;
    }

    private boolean who(SharedPreferences preferences, int position) {
        //내 아이디와 arraylist의 name이 같다면 내꺼 아니면 상대꺼
        if (items.get(position).getName() == preferences.getString("name","")) {
            return false;
        }
        return true;
    }

     */

    public void addItem(ChatContent item) {
        items.add(item);
    }
}
