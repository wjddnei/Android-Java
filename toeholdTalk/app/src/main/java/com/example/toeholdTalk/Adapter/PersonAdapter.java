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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Person> items;

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
        final Person item = items.get(position);
        holder.setItem(item);
        if(!item.getImageUrl().equals("")) Picasso.get().load("http://45.32.38.196:5000/"+item.getImageUrl()).fit().into(holder.imageView);
        holder.personItemCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , FriendProfileActivity.class);
                intent.putExtra("friendName", item.getName());
                intent.putExtra("friendImageUrl", item.getImageUrl());
                intent.putExtra("friendId", item.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView personTextView;
        CardView personItemCardView;
        CircleImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personTextView = itemView.findViewById(R.id.personTextView);
            personItemCardView = itemView.findViewById(R.id.personItemCardView);
            imageView = itemView.findViewById(R.id.imageView);
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
