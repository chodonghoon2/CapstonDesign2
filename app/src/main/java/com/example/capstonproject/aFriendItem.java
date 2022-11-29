package com.example.capstonproject;

public class aFriendItem {
    String match_number;
    String name;
    String message;
    int resourceId;

    public aFriendItem(String match_number, int resourceId, String name, String message) {
        this.match_number = match_number;
        this.name = name;
        this.message= message;
        this.resourceId = resourceId;
    }
    public String getNumber(){return match_number;}

    public int getResourceId() {
        return resourceId;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public void setMatch_number(){ this.match_number = match_number;}

    public void setMessage(String message) {
        this.message = message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}