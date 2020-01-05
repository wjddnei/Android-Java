package com.example.toeholdTalk;

import android.os.Bundle;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        RecyclerView recyclerView = findViewById(R.id.chatListRecyclerView);
    //  RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //더미 대화 생성
//        ArrayList<ChatContent> chatContents = new ArrayList<>();
  //      chatContents.add(new ChatContent("이승주","안녕","오후 5:50"));


        ChatRoomAdapter adapter = new ChatRoomAdapter();
        adapter.addItem(new ChatContent("이승주","안녕","오후 5:50"));



        recyclerView.setAdapter(adapter);



    }
}
