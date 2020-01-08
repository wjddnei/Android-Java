package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toeholdTalk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MyProfileActivity extends AppCompatActivity {

    Button profileExitButton;
    BottomNavigationView profileBottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profileBottomNavigation = findViewById(R.id.profileBottomNavigation);
        profileExitButton = findViewById(R.id.profileExitButton);
        setClickListner();

    }

    private void setClickListner() {
        profileExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        profileBottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.chat_tab:
                                Intent intent = new Intent(MyProfileActivity.this, ChatActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.edit_tab:
                                // 아직 작성 안함
                                return true;
                            default:
                                return false;
                        }
                    }
                }
        );

    }

}
