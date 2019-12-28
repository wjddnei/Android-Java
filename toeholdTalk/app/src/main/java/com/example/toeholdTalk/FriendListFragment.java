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

import java.util.ArrayList;
import java.util.Collections;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends Fragment {


    public FriendListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_friend_list,container,false);

        RecyclerView friendListView = rootView.findViewById(R.id.friendListView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        friendListView.setLayoutManager(layoutManager);

        //임시 Person 객체 생성

        ArrayList<Person> list = new ArrayList<Person>();

        list.add(new Person("승주"));
        list.add(new Person("원식"));
        list.add(new Person("혜미"));
        list.add(new Person("김지훈"));
        list.add(new Person("이지훈"));
        list.add(new Person("박지훈"));
        list.add(new Person("최지훈"));
        list.add(new Person("유지은"));
        list.add(new Person("이지우"));
        list.add(new Person("박상우"));
        list.add(new Person("김상우"));
        list.add(new Person("권원준"));
        list.add(new Person("박상결"));

        Collections.sort(list);

        PersonAdapter adapter = new PersonAdapter();
        adapter.addItem(list);

        friendListView.setAdapter(adapter);



        return rootView;
    }

}
