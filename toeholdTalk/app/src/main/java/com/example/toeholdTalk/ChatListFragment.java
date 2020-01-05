package com.example.toeholdTalk;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //기본 SharedPreferences 환경과 관련된 객체를 받아옴
        preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        // SharedPreferences 수정을 위한 Editor 객체를 받아옴
        editor = preferences.edit();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat_list,container,false);
        RecyclerView chatRoomListRecyclerView = rootView.findViewById(R.id.chatRoomListRecyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        chatRoomListRecyclerView.setLayoutManager(layoutManager);
        ChatAdapter adapter = new ChatAdapter();

        adapter.setOnItemClickListener(new ChatAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int pos) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
            //  intent.putExtra()
                startActivity(intent);
            }
        });

        // 임시 대화 생성

        adapter.addItem(new ChatList("안녕","이승주"));

        chatRoomListRecyclerView.setAdapter(adapter);

        return rootView;


    }

}
