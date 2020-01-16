package com.example.toeholdTalk.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Adapter.ChatActivityAdapter;
import com.example.toeholdTalk.Model.ChatContent;
import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import static java.security.AccessController.getContext;

public class ChatActivity extends AppCompatActivity {
    Toolbar toolbar;
    EditText editText;
    Button sendButton;
    MenuItem mSearch;
    String yourId, yourName, yourImageUrl;
    Socket socket;
    RecyclerView recyclerView;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // 뒤로가기 버튼
                socket.emit("requestChatList", MyInfo.getMyId());
                finish();
                return true;
            case R.id.chatSearchView :
                //내용 찾는 코드 추가
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.chat_menu_top, menu);

        //searchView 세팅
        mSearch = menu.findItem(R.id.chatSearchView);
        SearchView searchView = (SearchView) mSearch.getActionView();
        searchView.setQueryHint("검색");
        //확인 버튼 활성화
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        // 툴바에 홈버튼을 활성화
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // 툴바의 홈버튼의 이미지를 변경(기본 이미지는 뒤로가기 화살표)
       // getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_button_icon);


        //친구 이름 받아서 타이틀 설정
        ActionBar abBar = getSupportActionBar() ;

        editText = findViewById(R.id.ChatEditText);
        sendButton = findViewById(R.id.sendButton);

        recyclerView = findViewById(R.id.chatListRecyclerView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);

        yourId=getIntent().getStringExtra("yourId");
        yourName=getIntent().getStringExtra("yourName");
        yourImageUrl=getIntent().getStringExtra("yourImageUrl");

        setTitle(yourName);
        socket=wSocket.get();
        JSONObject jsonObject=new JSONObject();

        try{
            jsonObject.put("myId", MyInfo.getMyId());
            jsonObject.put("yourId", yourId);
            socket.emit("messageInfo", jsonObject);
            socket.emit("clearUnchecked", jsonObject);
            //System.out.println("messageInfo called");
        }catch(JSONException e){
            e.printStackTrace();
        }
        socket.on("update", updateChat);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });

    }

    private Emitter.Listener updateChat=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            final ArrayList<ChatContent> mMessages=new ArrayList<>();

            JSONArray receivedData=(JSONArray) args[0];
            try{
                for(int i=0; i<receivedData.length(); i++){
                    JSONObject data=receivedData.getJSONObject(i);
                    //System.out.println(data.toString());
                    mMessages.add(new ChatContent(data.getString("sender"), data.getString("receiver"), data.getString("message"), data.getString("time")));
                }

                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        recyclerView.setAdapter(new ChatActivityAdapter(ChatActivity.this, mMessages, yourImageUrl));
                    }
                });

            }catch (JSONException e){
                e.printStackTrace();
            }
        }
    };


    public void sendMessage(){
        String message=editText.getText().toString();
        JSONObject data=new JSONObject();
        try{
            Date time=new Date();
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd H:mm:ss");
            data.put("sender", MyInfo.getMyId());
            data.put("senderName", MyInfo.getMyName());
            data.put("receiver", yourId);
            data.put("receiverName", yourName);
            data.put("message", message);
            data.put("time", format.format(time));
            socket.emit("sendMessage", data);
        }catch (JSONException e){
            e.printStackTrace();
        }
        editText.setText("");
    }

    @Override
    public void onBackPressed() {
        socket.emit("requestChatList", MyInfo.getMyId());
        finish();
    }
}
