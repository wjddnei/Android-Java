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
import com.example.toeholdTalk.Activity.FriendProfileActivity;
import com.example.toeholdTalk.Model.Person;
import com.example.toeholdTalk.R;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    Context context;
    ArrayList<Person> items;

    public PersonAdapter(Context context, ArrayList<Person> personList) {
        this.context = context;
        items = personList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.person_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Person item = items.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView personTextView;
        CardView personItemCardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personTextView = itemView.findViewById(R.id.personTextView);
            personItemCardView = itemView.findViewById(R.id.personItemCardView);

            //친구 프로필 클릭 리스너
            personItemCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition() ;
                    if (pos != RecyclerView.NO_POSITION) {
                        Intent intent = new Intent( context , FriendProfileActivity.class);
                        context.startActivity(intent);
                    }
                }
            });
        }
        public void setItem(Person item) {
            personTextView.setText(item.getName());
        }
    }

    public void addItem(Person item) {
        items.add(item);
    }

    public void addItem(ArrayList<Person> item) {
        for(Person node : item) {
            items.add(node);
        }
    }

    public void setItems(ArrayList<Person> items) {
        this.items = items;
    }

    public Person getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Person item) {
        items.set(position, item);
    }

}
