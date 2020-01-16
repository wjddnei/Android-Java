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
import com.example.toeholdTalk.Util.HandleTime;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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
        final ChatList item = items.get(position);
        holder.setItem(item);

        holder.chatItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, ChatActivity.class);
                intent.putExtra("yourName", item.getFriendName());
                intent.putExtra("yourId", item.getId());
                intent.putExtra("yourImageUrl", item.getImageUrl());
                context.startActivity(intent);
            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, messageTextView, timeTextView, uncheckedTextView;
        CircleImageView circleImageView;
        CardView chatItemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
            timeTextView = itemView.findViewById(R.id.TextViewTime);
            uncheckedTextView = itemView.findViewById(R.id.TextViewUnchecked);
            chatItemCardView = itemView.findViewById(R.id.chatItemCardView);
            circleImageView = itemView.findViewById(R.id.imageView2);
        }
        public void setItem(ChatList item) {
            nameTextView.setText(item.getFriendName());
            messageTextView.setText(item.getMessage());
            timeTextView.setText(HandleTime.toSimpleFormat(item.getTime()));
            int unchecked = item.getUnchecked();
            if(unchecked!=0) uncheckedTextView.setText(Integer.toString(unchecked));
            else uncheckedTextView.setVisibility(View.GONE);
            if(!item.getImageUrl().equals("")) Picasso.get().load("http://45.32.38.196:5000/"+item.getImageUrl()).fit().into(circleImageView);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
