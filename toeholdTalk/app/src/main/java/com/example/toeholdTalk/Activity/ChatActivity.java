package com.example.toeholdTalk.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Model.ChatContent;
import com.example.toeholdTalk.Adapter.ChatActivityAdapter;
import com.example.toeholdTalk.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ChatActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backButton :
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu_top, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar3);

        RecyclerView recyclerView = findViewById(R.id.chatListRecyclerView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //더미 대화 생성
        ArrayList<ChatContent> chatContents = new ArrayList<>();
        chatContents.add(new ChatContent("이승주","안녕","오후 5:50"));


        ChatActivityAdapter adapter = new ChatActivityAdapter(getContext());
        adapter.setItems(chatContents);

        recyclerView.setAdapter(adapter);
    }
}
