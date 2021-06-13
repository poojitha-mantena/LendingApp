package com.example.lendingapp.Chat;


import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;

// [START comment_class]
@IgnoreExtraProperties
public class FriendlyMessage implements Serializable {

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    private String text;
    private String name;
    private String timeStamp;
    private String fromUserId;

    public FriendlyMessage() {

    }

    public FriendlyMessage(String text, String name, String fromUserId, String timeStamp) {
        this.text = text;
        this.name = name;
        this.fromUserId = fromUserId;
        this.timeStamp = timeStamp;
    }

}
