package com.example.toeholdTalk.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Activity.ChatActivity;
import com.example.toeholdTalk.Model.ChatList;
import com.example.toeholdTalk.R;

import java.util.ArrayList;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {

    Context context;
    ArrayList<ChatList> items;

    public ChatListAdapter(Context context, ArrayList<ChatList> chatList) {
        this.context = context;
        items = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.chat_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ChatList item = items.get(position);
        holder.setItem(item);
        /* onBindViewHolder 안에서 대화방 클릭 리스너를 정의
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( context , ChatActivity.class);
                context.startActivity(intent);
            }
        });
         */
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, messageTextView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            cardView = itemView.findViewById(R.id.chatItemCardView);

            //대화방 클릭 리스너
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent( context , ChatActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
        public void setItem(ChatList item) {
            nameTextView.setText(item.getFriendName());
            messageTextView.setText(item.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
