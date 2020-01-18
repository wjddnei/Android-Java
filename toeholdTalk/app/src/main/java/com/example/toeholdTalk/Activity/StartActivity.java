package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class StartActivity extends AppCompatActivity {


    Button loginButton, signUpButton;
    EditText idEditText, passwordEditText;
    Socket socket;
    int result;
    String myId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);
        idEditText = findViewById(R.id.idEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        wSocket.connect();
        socket = wSocket.get();
        socket.on("loginResult",loginResult);


        //로그인 버튼 리스너
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_id = idEditText.getText().toString();
                String txt_password = passwordEditText.getText().toString();
                login(txt_id, txt_password);
            }
        });

        //회원가입 버튼 리스너
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void login(String id, String password) {
        try {
            JSONObject loginInfo = new JSONObject();
            loginInfo.put("id", id);
            loginInfo.put("password", password);
            myId = id;
            socket.emit("login", loginInfo);
        } catch (JSONException e) {
            e.printStackTrace();;
        }
    }

    Emitter.Listener loginResult = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject receivedData = (JSONObject)args[0]; //(args가 1개로 서버에서 세팅)
            result = -1;

            try {
                result = receivedData.getInt("result");
            }catch (JSONException e) {
                e.printStackTrace();
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    switch (result) {
                        case 1:
                            Toast.makeText(StartActivity.this, "존재하지 않는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            idEditText.setText("");
                            passwordEditText.setText("");
                            break;
                        case 2:
                            Toast.makeText(StartActivity.this, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                            idEditText.setText("");
                            passwordEditText.setText("");
                            break;
                        case 3:
                            Intent intent=new Intent(StartActivity.this, MainActivity.class);
                            MyInfo.setMyId(myId);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            Toast.makeText(StartActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                            idEditText.setText("");
                            passwordEditText.setText("");
                            break;
                    }
                }
            });
        }
    };
}
