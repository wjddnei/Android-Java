package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

public class FriendProfileActivity extends AppCompatActivity {
    Button profileExitButton;
    BottomNavigationView profileBottomNavigation;
    TextView textView;
    ImageView imageView;
    String name, imageUrl, id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_profile);

        profileBottomNavigation = findViewById(R.id.profileBottomNavigation);
        profileExitButton = findViewById(R.id.profileExitButton);
        textView = findViewById(R.id.nameTextView);
        imageView = findViewById(R.id.personImageView);
        name = getIntent().getStringExtra("friendName");
        imageUrl = getIntent().getStringExtra("friendImageUrl");
        id = getIntent().getStringExtra("friendId");
        textView.setText(name);
        if(!imageUrl.equals("")) Picasso.get().load("http://45.32.38.196:5000/"+ imageUrl).fit().into(imageView);
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
                                Intent intent = new Intent(FriendProfileActivity.this, ChatActivity.class);
                                intent.putExtra("yourId", id);
                                intent.putExtra("yourName", name);
                                intent.putExtra("yourImageUrl", imageUrl);
                                startActivity(intent);
                                finish();
                                return true;
                            case R.id.call_tab:
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
