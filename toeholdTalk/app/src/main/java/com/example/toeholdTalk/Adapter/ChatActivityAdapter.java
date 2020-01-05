package com.example.toeholdTalk.Adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Model.ChatContent;
import com.example.toeholdTalk.R;

import java.security.AccessControlContext;
import java.util.ArrayList;

public class ChatActivityAdapter extends RecyclerView.Adapter<ChatActivityAdapter.ViewHolder> {

    private SharedPreferences preferences;
    private AccessControlContext context;
    private ArrayList<ChatContent> items;

    public ChatActivityAdapter(AccessControlContext context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChatActivityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

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
            chatContent = itemView.findViewById(R.id.partnerChatContent);
            chatTime = itemView.findViewById(R.id.partnerChatTime);
            partnerImage = itemView.findViewById(R.id.partnerImage);
            partnerName = itemView.findViewById(R.id.partnerName);
            partnerChatContent = itemView.findViewById(R.id.partnerChatContent);
            partnerChatTime = itemView.findViewById(R.id.partnerChatTime);
        }

        public void setItem(ChatContent item) {
            name.setText(item.getName());
            chatContent.setText(item.getChat());
            chatTime.setText(item.getTime());
        }
    }

    @Override
    public int getItemViewType(int position) {
    //    preferences = context.getSharedPreferences("USERSIGN", Context.MODE_PRIVATE);
        return who(preferences, position)? 0: 1;
    }

    private boolean who(SharedPreferences preferences, int position) {
        //내 아이디와 arraylist의 name이 같다면 내꺼 아니면 상대꺼
        /*
        if (items.get(position).getName() == JSon.getString("name","")) {
            return false;
        }
         */
        return true;
    }

    public ArrayList<ChatContent> getItems() {
        return items;
    }
    public void setItems(ArrayList<ChatContent> param) {
        this.items = param;
    }
}
