package com.example.toeholdTalk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button signUpButton, goBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signUpButton = findViewById(R.id.signUpButton);
        goBackButton = findViewById(R.id.goBackButton);

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
}
