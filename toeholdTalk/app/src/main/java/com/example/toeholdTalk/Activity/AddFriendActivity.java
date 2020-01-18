package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class AddFriendActivity extends AppCompatActivity {

    EditText serachFriendEditText;
    Button serachFriendButton, addFriendButton;
    Toolbar toolbar;
    String myId;
    Socket socket;
    LinearLayout layoutFound, layoutNotFound;
    CircleImageView profileImageView;
    TextView profileTextView;

    String yourId, yourName, yourImageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        setTitle("토홀드 ID로 추가");

        socket = wSocket.get();

        toolbar = findViewById(R.id.addFreindToolbar);
        setSupportActionBar(toolbar);

        serachFriendEditText = findViewById(R.id.serachFriendEditText);
        serachFriendButton = findViewById(R.id.serachFriendButton);
        serachFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_id = serachFriendEditText.getText().toString();
                socket.on("resultSearchId", resultSearchId);
                socket.emit("searchId", txt_id);
            }
        });

        addFriendButton = findViewById(R.id.addButton);
        addFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add(MyInfo.getMyId(), yourId, yourName);
            }
        });

        layoutFound = findViewById(R.id.resultFoundLinerLayout);
        layoutNotFound = findViewById(R.id.resultNotFoundLinearLayout);
        profileTextView = findViewById(R.id.profileName);
        profileImageView = findViewById(R.id.profileImage);

    }

    private Emitter.Listener resultSearchId=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            final JSONObject data=(JSONObject)args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try{
                        if(!data.getString("id").equals("")){
                            layoutFound.setVisibility(View.VISIBLE);
                            layoutNotFound.setVisibility(View.GONE);
                            yourId = data.getString("id");
                            yourName = data.getString("name");
                            yourImageUrl = data.getString("imageUrl");
                            profileTextView.setText(yourId);
                            if(!yourImageUrl.equals("")) Picasso.get().load("http://45.32.38.196:5000/"+ yourImageUrl).fit().into(profileImageView);
                        }
                        else{
                            layoutNotFound.setVisibility(View.VISIBLE);
                            layoutFound.setVisibility(View.GONE);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    };

    public void add(String myId, String yourId, String yourName){
        socket.on("resultAddFriend", resultAddFriend);
        JSONObject user=new JSONObject();
        try{
            user.put("myId", myId);
            user.put("yourId", yourId);
            user.put("yourName", yourName);
            socket.emit("addFriend", user);
        }catch (JSONException e){
            e.printStackTrace();
        }
    }


    private Emitter.Listener resultAddFriend=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            final int res=(Integer)args[0];
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch(res){
                        case 0:
                            Toast.makeText(AddFriendActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                            break;
                        case 1:
                            Toast.makeText(AddFriendActivity.this, "이 ID와 이미 친구입니다.", Toast.LENGTH_SHORT).show();
                            break;
                        case 2:
                            Toast.makeText(AddFriendActivity.this, "친구 추가 완료", Toast.LENGTH_SHORT).show();
                            socket.emit("requestFriendList", MyInfo.getMyId());
                            finish();
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
