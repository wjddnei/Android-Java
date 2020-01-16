package com.example.toeholdTalk.Model;

public class ChatList implements Comparable<ChatList>{
    private String id;
    private String friendName;
    private String message;
    private String time;
    private int unchecked;
    private  String imageUrl;

    public ChatList(String id, String friendName, String message, String time, int unchecked, String imageUrl) {
        this.id = id;
        this.friendName = friendName;
        this.message = message;
        this.time = time;
        this.unchecked = unchecked;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUnchecked() {
        return unchecked;
    }

    public void setUnchecked(int unchecked) {
        this.unchecked = unchecked;
    }

    @Override
    public int compareTo(ChatList chatList) {
        if((chatList.getUnchecked()==0&&unchecked==0)||(chatList.getUnchecked()!=0&&unchecked!=0)){
            return -1*time.compareTo(chatList.time);
        }
        else if(unchecked==0){
            return 1;
        }

        else {
            return -1;
        }
    }
}
