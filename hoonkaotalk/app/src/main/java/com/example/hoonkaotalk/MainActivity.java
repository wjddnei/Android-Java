package com.example.hoonkaotalk;

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

        //엑티비티 생성
        friendListActivity = new FriendListActivity();
        chatListActivity = new ChatListActivity();


        //친구 목록을 시작 화면으로 설정
        getSupportFragmentManager().beginTransaction().replace(R.id.container,friendListActivity).commit();


        //하단 탭을 보여주는 위젯 설정
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            //친구 목록
                            case R.id.tab1:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, friendListActivity).commit();
                                return true;
                             //채팅 목록
                            case R.id.tab2:
                                getSupportFragmentManager().beginTransaction().replace(R.id.container, chatListActivity).commit();
                                return true;
                             //설정
                            case R.id.tab3:
                                // 아직 구현 안함
                                // getSupportFragmentManager().beginTransaction().replace(R.id.container, settingActivity).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );
    }
}
