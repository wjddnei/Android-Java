package com.example.toeholdTalk.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Adapter.ChatActivityAdapter;
import com.example.toeholdTalk.Model.ChatContent;
import com.example.toeholdTalk.R;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class ChatActivity extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // 뒤로가기 버튼
                finish();
                return true;
            case R.id.searchButton :
                //내용 찾는 코드 추가
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
        setSupportActionBar(toolbar);

        // ↓툴바에 홈버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // ↓툴바의 홈버튼의 이미지를 변경(기본 이미지는 뒤로가기 화살표)
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_icon);


        //친구 이름 받아서 타이틀 설정
        setTitle("친구 이름");
        ActionBar abBar = getSupportActionBar() ;

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
