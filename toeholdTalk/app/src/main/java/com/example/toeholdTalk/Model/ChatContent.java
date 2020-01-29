package com.example.toeholdTalk.Model;

import androidx.annotation.NonNull;

public class ChatContent {
    private String sender, receiver, message, time;

        public ChatContent(String sender, String receiver, String message, String time) {
            this.sender = sender;
            this.receiver = receiver;
            this.message = message;
            this.time = time;
        }

        public String getSender() {
            return sender;
        }

        public void setSender(String sender) {
            this.sender = sender;
        }

        public String getReceiver() {
            return receiver;
        }

        public void setReceiver(String receiver) {
            this.receiver = receiver;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

}
