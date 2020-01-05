package com.example.toeholdTalk.Model;

import androidx.annotation.NonNull;

public class ChatContent {

    private String name;
    private String chat;
    private String time;

    public ChatContent(String name, String chat, String time) {
        this.name = name;
        this.chat = chat;
        this.time = time;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setChat(String chat) {
        this.chat = chat;
    }
    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }
    public String getChat() {
        return chat;
    }
    public String getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "ChatContent{" +
                "name='" + name + '\'' +
                ", chat='" + chat + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
