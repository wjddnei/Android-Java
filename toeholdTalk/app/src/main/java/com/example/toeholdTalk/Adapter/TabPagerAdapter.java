package com.example.toeholdTalk.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.toeholdTalk.Fragment.ChatListFragment;
import com.example.toeholdTalk.Fragment.FriendListFragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;

    public TabPagerAdapter(FragmentManager fragmentManager, int tabCount) {
        super(fragmentManager, tabCount);
        this.tabCount = tabCount;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                FriendListFragment friendListFragment = new FriendListFragment();
                return friendListFragment;
            case 1:
                ChatListFragment chatListFragment = new ChatListFragment();
                return chatListFragment;
            case 2:
                ChatListFragment chatListFragment2 = new ChatListFragment();
                return chatListFragment2;
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
