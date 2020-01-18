package com.example.toeholdTalk.Model;

public class MyInfo {
    static String myId;
    static String myName;
    static String myImagUrl;

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

    public static String getMyImagUrl() {
        return myImagUrl;
    }

    public static void setMyImagUrl(String myImagUrl) {
        MyInfo.myImagUrl = myImagUrl;
    }
}
