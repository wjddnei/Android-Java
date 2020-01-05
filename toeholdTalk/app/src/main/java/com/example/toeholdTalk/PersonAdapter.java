package com.example.toeholdTalk;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    ArrayList<Person> items = new ArrayList<Person>();

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

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView personTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            personTextView = itemView.findViewById(R.id.personTextView);
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
