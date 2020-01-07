package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class AddFriendActivity extends AppCompatActivity {

    EditText serachFriendEditText;
    Button serachFriendButton;
    Toolbar toolbar;
    String myId;
    Socket socket;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        setTitle("토홀드 ID로 추가");

        socket = wSocket.get();
        socket.on("searchFreind", searchFreind); //registerResult 핸들러

        toolbar = findViewById(R.id.addFreindToolbar);
        setSupportActionBar(toolbar);

        serachFriendEditText = findViewById(R.id.serachFriendEditText);
        serachFriendButton = findViewById(R.id.serachFriendButton);
        serachFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_id = serachFriendEditText.getText().toString();
                findFreindId(txt_id);
            }
        });

    }

    private void findFreindId(String id) {
        try {
            JSONObject registerInfo = new JSONObject();
            registerInfo.put("id", id);
            myId = id;
            socket.emit("register", registerInfo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Emitter.Listener searchFreind = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject receivedData = (JSONObject)args[0]; //(args가 1개로 서버에서 세팅)
            result = -1;

            try {
                result = receivedData.getInt("result");
            }catch (JSONException e) {
                e.printStackTrace();
            }

            /*결과값에 따라 다른 토스트 메시지 출력*/
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (result) {
                        case 1:
                            Toast.makeText(AddFriendActivity.this, "아이디가 존재하지 않습니다.", Toast.LENGTH_SHORT).show();
                            serachFriendEditText.setText("");
                            break;
                        case 2:
                            Toast.makeText(AddFriendActivity.this, "친구 추가에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            MyInfo.setMyId(myId);
                            finish();
                            break;
                        default:
                            Toast.makeText(AddFriendActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                            serachFriendEditText.setText("");
                            break;
                    }
                }
            });
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_friend_menu_top, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.xButton:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
