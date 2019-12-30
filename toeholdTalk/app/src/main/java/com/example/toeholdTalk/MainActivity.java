package com.example.toeholdTalk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private FriendListFragment friendListFragment;
    private ChatListFragment chatListFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*
        friendListFragment = new FriendListFragment();
        chatListFragment = new ChatListFragment();

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.viewPager,friendListFragment);
        transaction.add(R.id.viewPager,chatListFragment);
*/

        tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.person_icon),0,true);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.chat_icon),1);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.settings_icon),2);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = (ViewPager)findViewById(R.id.viewPager);

        TabPagerAdapter pagerAdapter = new TabPagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });


//       getSupportFragmentManager().beginTransaction().replace(R.id.container,friendListFragment).commit();
/*
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.container,friendListFragment);
        transaction.add(R.id.container,chatListFragment);
*/

        /*
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.tab1:
                                transaction.replace(R.id.container,friendListFragment).commitAllowingStateLoss();
                              //getSupportFragmentManager().beginTransaction().hide(settingListFragment).commit();
                                return true;
                            case R.id.tab2:
                                 transaction.replace(R.id.container,chatListFragment).commitAllowingStateLoss();
                                //getSupportFragmentManager().beginTransaction().hide(settingListFragment).commit();
                                return true;
                            case R.id.tab3:
                                //아직 구현 안함
                             //   transaction.hide(friendListFragment).commit();
                           //     transaction.hide(chatListFragment).commit();
                                //  getSupportFragmentManager().beginTransaction().show(settingListFragment).commit();
                                return true;
                        }
                        return false;
                    }
                }
        );

*/
    }
}
