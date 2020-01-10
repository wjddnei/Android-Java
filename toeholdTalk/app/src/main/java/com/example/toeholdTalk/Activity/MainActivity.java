package com.example.toeholdTalk.Activity;

import android.os.Bundle;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.toeholdTalk.Fragment.ChatListFragment;
import com.example.toeholdTalk.Fragment.FriendListFragment;
import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;
import com.google.android.material.tabs.TabLayout;

import io.socket.client.Socket;

public class MainActivity extends AppCompatActivity {

    private static final int FRAG_USER = 0;
    public static final int FRAG_CHAT = 1;
    public static final int FRAG_CONFIG = 2;

    private TabLayout tabLayout;
    private FragmentManager fragmentManager;
    private Fragment user, chat, config;
    private Toolbar mainActivityToolbar;
//  private ViewPager viewPager;

    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainActivityToolbar = findViewById(R.id.mainActivityToolbar);
        setSupportActionBar(mainActivityToolbar);


        socket= wSocket.get();
        socket.emit("transferId", MyInfo.getMyId());
        setupTabLayout();

        fragmentManager = getSupportFragmentManager();
        user = new FriendListFragment();
        chat = new ChatListFragment();
        // config = new ConfigFragment();

        fragmentManager.beginTransaction().replace(R.id.fragment, user).commit();
        fragmentManager.beginTransaction().add(R.id.fragment, chat).commit();
        fragmentManager.beginTransaction().hide(chat).commit();
        setTitle("친구");

    }

    private void setupTabLayout() {
        tabLayout = findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.person_icon),0,true);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chat_icon),1);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.settings_icon),2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int pos = tab.getPosition();
                switch (pos) {
                    case FRAG_USER:
                        switchFragment(FRAG_USER);
                        setTitle("친구");
                        break;
                    case FRAG_CHAT:
                        switchFragment(FRAG_CHAT);
                        setTitle("채팅");
                        break;
                    case FRAG_CONFIG:
                        setTitle("설정");
                        switchFragment(FRAG_CONFIG);
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    public void switchFragment(int next){
        switch(next) {
            case FRAG_USER:
                fragmentManager.beginTransaction().show(user).commit();
                fragmentManager.beginTransaction().hide(chat).commit();
       //         fragmentManager.beginTransaction().hide(config).commit();
                break;

            case FRAG_CHAT:
                fragmentManager.beginTransaction().show(chat).commit();
                fragmentManager.beginTransaction().hide(user).commit();
      //          fragmentManager.beginTransaction().hide(config).commit();
                break;

            case FRAG_CONFIG:
                /*
                fragmentManager.beginTransaction().show(config).commit();
                fragmentManager.beginTransaction().hide(user).commit();
                fragmentManager.beginTransaction().hide(chat).commit();

                 */
                break;
        }
    }
    @Override
    public void onBackPressed() {
    }
}
