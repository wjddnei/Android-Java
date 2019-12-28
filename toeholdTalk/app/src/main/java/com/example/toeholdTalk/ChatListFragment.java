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


/**
 * A simple {@link Fragment} subclass.
 */
public class ChatListFragment extends Fragment {


    public ChatListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat_list,container,false);


        RecyclerView chatListView = rootView.findViewById(R.id.chatListView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        chatListView.setLayoutManager(layoutManager);
        ChatAdapter adapter = new ChatAdapter();

        // 임시 대화 생성

        adapter.addItem(new Chat("어디야?","김승주"));
        adapter.addItem(new Chat("뭐해?","주인"));
        adapter.addItem(new Chat("가고있어","소은"));

        chatListView.setAdapter(adapter);
        return rootView;


    }

}
