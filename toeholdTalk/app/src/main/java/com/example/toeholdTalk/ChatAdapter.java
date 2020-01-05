package com.example.toeholdTalk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    ArrayList<ChatList> items = new ArrayList<ChatList>();


    // 채팅방 클릭 리스너를 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClicked(View view, int pos);
    }
    private OnItemClickListener mListener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView, messageTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            messageTextView = itemView.findViewById(R.id.messageTextView);
        }
        public void setItem(ChatList item) {
            nameTextView.setText(item.getFriendName());
            messageTextView.setText(item.getMessage());
        }
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

        //클릭 이벤트
        if(mListener != null) {
            final int pos = position;
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemClicked(view, pos);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }



    public void addItem(ChatList item) {
        items.add(item);
    }

    public void setItems(ArrayList<ChatList> items) {
        this.items = items;
    }

    public ChatList getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, ChatList item) {
        items.set(position, item);
    }

}
