package com.example.toeholdTalk.Fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.toeholdTalk.R;
import com.example.toeholdTalk.Model.wSocket;

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

        socket = wSocket.get();
        socket.on("resultChatList", resultChatList);
        socket.emit("requestChatList", MyInfo.getMyId());

        chatList = new ArrayList<>();

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_chat_list,container,false);
        chatRoomListRecyclerView = rootView.findViewById(R.id.chatRoomListRecyclerView);

        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        chatRoomListRecyclerView.setLayoutManager(layoutManager);

        /*
        adapter.setOnItemClickListener(new ChatListAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int pos) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
            //  intent.putExtra()
                startActivity(intent);
            }
        });

         */

        // 임시 대화 생성

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

}
