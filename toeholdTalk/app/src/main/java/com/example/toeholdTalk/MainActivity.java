package com.example.toeholdTalk;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private FriendListActivity friendListActivity;
    private ChatListActivity chatListActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);


        friendListActivity = new FriendListActivity();
        chatListActivity = new ChatListActivity();


        getSupportFragmentManager().beginTransaction().replace(R.id.container,friendListActivity).commit();

        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, friendListActivity).commit();
                                return true;
                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, chatListActivity).commit();
                                return true;
                            case R.id.tab3:
                                //아직 구현 안함
                                // getSupportFragmentManager().beginTransaction().replace(R.id.container, settingActivity).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}
