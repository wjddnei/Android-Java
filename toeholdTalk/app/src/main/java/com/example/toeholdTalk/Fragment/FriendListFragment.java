package com.example.toeholdTalk.Fragment;


import android.app.Activity;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Activity.AddFriendActivity;
import com.example.toeholdTalk.Activity.MyProfileActivity;
import com.example.toeholdTalk.Adapter.PersonAdapter;
import com.example.toeholdTalk.Model.MyInfo;
import com.example.toeholdTalk.Model.Person;
import com.example.toeholdTalk.Model.wSocket;
import com.example.toeholdTalk.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class FriendListFragment extends Fragment {
    Activity mActivity;
    Socket socket;
    ArrayList<Person> personList;
    PersonAdapter adapter;
    RecyclerView friendListView;
    LinearLayout profile;
    ImageView image;
    TextView name;
    TextView numfriend;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    public FriendListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        setHasOptionsMenu(true);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_friend_list,container,false);

        friendListView = rootView.findViewById(R.id.friendListView);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        friendListView.setLayoutManager(layoutManager);
        numfriend = rootView.findViewById(R.id.friendsTextView);
        socket = wSocket.get();
        socket.on("resultFriendList", resultFriendList);
        socket.emit("requestFriendList", MyInfo.getMyId());

        personList = new ArrayList<>();

        setupProfile(rootView);
        return rootView;
    }

    public void setupProfile(View view){
        profile=view.findViewById(R.id.profile);
        image=view.findViewById(R.id.image);
        name=view.findViewById(R.id.name);
        socket.emit("getMyName", MyInfo.getMyId());
        socket.on("resultMyName", resultMyName);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MyProfileActivity.class));
            }
        });
    }


    Emitter.Listener resultFriendList = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONArray receivedData = (JSONArray)args[0]; //(args가 1개로 서버에서 세팅)
            personList.clear();
            try {
                for(int i=0; i<receivedData.length(); ++i) {
                    JSONObject data = receivedData.getJSONObject(i);
                    personList.add(new Person(data.getString("id"), data.getString("name"), data.getString("imageUrl")));
                }

                Collections.sort(personList);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        numfriend.setText("친구  " + Integer.toString(personList.size()) );
                        adapter = new PersonAdapter(getContext(), personList);
                        friendListView.setAdapter(adapter);
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    };


    private Emitter.Listener resultMyName=new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            final JSONObject receivedData=(JSONObject)args[0];
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("called");
                        String myName=receivedData.getString("name");
                        String myUrl=receivedData.getString("imageUrl");
                        MyInfo.setMyImagUrl(myUrl);
                        System.out.println(myName+" "+myUrl);
                        MyInfo.setMyName(myName);
                        name.setText(myName);
                        if(!myUrl.equals("")) Picasso.get().load("http://45.32.38.196:5000/"+myUrl).fit().into(image);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
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
