package com.example.toeholdTalk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FriendListActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.activity_friend_list,container,false);


        //임시 Person 객체 생성
        RecyclerView friendListView = rootView.findViewById(R.id.friendListView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        friendListView.setLayoutManager(layoutManager);
        PersonAdapter adapter = new PersonAdapter();

        adapter.addItem(new Person("승주"));
        adapter.addItem(new Person("원식"));
        adapter.addItem(new Person("혜미"));
        adapter.addItem(new Person("김지훈"));
        adapter.addItem(new Person("이지훈"));
        adapter.addItem(new Person("박지훈"));
        adapter.addItem(new Person("최지훈"));
        adapter.addItem(new Person("유지은"));
        adapter.addItem(new Person("이지우"));

        friendListView.setAdapter(adapter);



        return rootView;
    }
}
