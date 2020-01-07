package com.example.toeholdTalk.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.toeholdTalk.Adapter.ChatListAdapter;
import com.example.toeholdTalk.Model.ChatList;
import com.example.toeholdTalk.Model.MyInfo;
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
public class ChatListFragment extends Fragment {

    Socket socket;
    ChatListAdapter adapter;
    ArrayList<ChatList> chatList;
    RecyclerView chatRoomListRecyclerView;

    public ChatListFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //onCreateOptionMenu에서 바뀔 menu 를 승인
        setHasOptionsMenu(true);

        socket = wSocket.get();
        socket.on("resultChatList", resultChatList);
        socket.emit("requestChatList", MyInfo.getMyId());

        chatList = new ArrayList<>();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat_list,container,false);
        chatRoomListRecyclerView = rootView.findViewById(R.id.chatRoomListRecyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        chatRoomListRecyclerView.setLayoutManager(layoutManager);

        return rootView;
    }

    Emitter.Listener resultChatList = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONArray receivedData = (JSONArray)args[0]; //(args가 1개로 서버에서 세팅)
            try {
                for(int i=0; i<receivedData.length(); ++i) {
                    JSONObject data = receivedData.getJSONObject(i);
                    chatList.add(new ChatList(data.getString("id"), data.getString("name"), data.getString("message")));

                }
                adapter = new ChatListAdapter(getContext(), chatList);
                chatRoomListRecyclerView.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.chat_list_menu_top, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }
}