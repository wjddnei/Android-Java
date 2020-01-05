package com.example.toeholdTalk.Model;

public class ChatList {
    private String id;
    private String message;
    private String friendName;

    public ChatList(String id, String friendName, String message) {
        this.id = id;
        this.message = message;
        this.friendName = friendName;
    }

    public String getMessage() {
        return this.message;
    }
    public String getFriendName() {
        return this.friendName;
    }

    public void setMessage(String name) {
        this.message = message;
    }
    public void setFriendName(String name) {
        this.message = friendName;
    }
}
