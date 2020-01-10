package com.example.toeholdTalk.Fragment;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Activity.AddFriendActivity;
import com.example.toeholdTalk.Adapter.PersonAdapter;
import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.Person;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends Fragment {

    Socket socket;
    ArrayList<Person> personList;
    PersonAdapter adapter;
    RecyclerView friendListView;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public FriendListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_friend_list,container,false);

        friendListView = rootView.findViewById(R.id.friendListView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        friendListView.setLayoutManager(layoutManager);


        socket = wSocket.get();
        socket.on("resultFriendList", resultFriendList);
        socket.emit("requestFriendList", MyInfo.getMyId());

        personList = new ArrayList<>();

        return rootView;
    }

    Emitter.Listener resultFriendList = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONArray receivedData = (JSONArray)args[0]; //(args가 1개로 서버에서 세팅)
            try {
                for(int i=0; i<receivedData.length(); ++i) {
                    JSONObject data = receivedData.getJSONObject(i);
                    personList.add(new Person(data.getString("id"), data.getString("name")));

                }
                adapter = new PersonAdapter(personList);
                friendListView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.friend_list_menu_top, menu);

        //searchView 세팅
        SearchView searchView = (SearchView) menu.findItem(R.id.friendListSearchView).getActionView();
        searchView.setQueryHint("검색");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.friendAdditionButton:
                Intent intent = new Intent(getContext(), AddFriendActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
