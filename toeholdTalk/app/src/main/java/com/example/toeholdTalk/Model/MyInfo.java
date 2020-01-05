package com.example.toeholdTalk.Model;

public class MyInfo {
    static String myId;
    static String myName;

    public static String getMyId() {
        return myId;
    }

    public static void setMyId(String myId) {
        MyInfo.myId = myId;
    }

    public static String getMyName() {
        return myName;
    }

    public static void setMyName(String myName) {
        MyInfo.myName = myName;
    }
}
