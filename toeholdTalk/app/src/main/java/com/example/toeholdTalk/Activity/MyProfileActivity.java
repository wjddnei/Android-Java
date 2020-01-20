package com.example.toeholdTalk.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
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

public class MyProfileActivity extends AppCompatActivity {
    Button profileExitButton;
    BottomNavigationView profileBottomNavigation;
    ImageView myImage;
    TextView myName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        profileBottomNavigation = findViewById(R.id.profileBottomNavigation);
        profileExitButton = findViewById(R.id.profileExitButton);
        myImage = findViewById(R.id.personImageView);
        myName = findViewById(R.id.nameTextView);
        if(!MyInfo.getMyImagUrl().equals("")) Picasso.get().load("http://45.32.38.196:5000/"+ MyInfo.getMyImagUrl()).fit().into(myImage);
        myName.setText(MyInfo.getMyName());
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
                                finish();
                                
                                return true;
                            case R.id.edit_tab:
                                Intent intent2 = new Intent(MyProfileActivity.this, ProfileUpdateActivity.class);
                                startActivity(intent2);
                                return true;
                            default:
                                return false;
                        }
                    }
                }
        );

    }

}
