package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.R;
import com.example.toeholdTalk.Model.wSocket;

import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class RegisterActivity extends AppCompatActivity {

    private Button signUpButton, goBackButton;
    private EditText nameEditText, idEditText, passwordEditText1, passwordEditText2;
    private Socket socket;
    private int result;
    private String myId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpButton = findViewById(R.id.signUpButton);
        goBackButton = findViewById(R.id.goBackButton);

        nameEditText = findViewById(R.id.nameEditText);
        idEditText = findViewById(R.id.idEditText);
        passwordEditText1 = findViewById(R.id.passwordEditText1);
        passwordEditText2 = findViewById(R.id.passwordEditText2);

        socket = wSocket.get();
        socket.on("registerResult", registerResult); //registerResult 핸들러

        //회원가입 버튼 리스너
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String txt_name = nameEditText.getText().toString();
                String txt_id = idEditText.getText().toString();
                String txt_password = passwordEditText1.getText().toString();
                String txt_password_chk = passwordEditText2.getText().toString();
                // 비밀번호 확인

                if(txt_password.equals(txt_password_chk)) {
                    register(txt_id, txt_password, txt_name);
                }
                else {
                    Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //돌아가기 버튼 리스너
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void register(String id, String password, String name) {
        try {
            JSONObject registerInfo = new JSONObject();
            registerInfo.put("id", id);
            registerInfo.put("password", password);
            registerInfo.put("name", name);
            myId = id;
            socket.emit("register", registerInfo);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private Emitter.Listener registerResult = new Emitter.Listener() {
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
                            Toast.makeText(RegisterActivity.this, "이미 존재하는 아이디입니다.", Toast.LENGTH_SHORT).show();
                            idEditText.setText("");
                            passwordEditText1.setText("");
                            passwordEditText2.setText("");
                            break;
                        case 2:
                            Toast.makeText(RegisterActivity.this, "회원가입에 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegisterActivity.this, StartActivity.class);
                            MyInfo.setMyId(myId);
                            startActivity(intent);
                            finish();
                            break;
                        default:
                            Toast.makeText(RegisterActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                            idEditText.setText("");
                            passwordEditText1.setText("");
                            passwordEditText2.setText("");
                            break;
                    }
                }
            });
        }
    };

}
