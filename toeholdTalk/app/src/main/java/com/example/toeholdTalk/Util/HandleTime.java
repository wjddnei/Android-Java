package com.example.toeholdTalk.Util;

public class HandleTime {
    public static String toSimpleFormat(String s){
        String time = s.split(" ")[1];
        return time.split(":")[0] + ":" + time.split(":")[1];
    }
}
