package com.droidman.gettingstartedretrofit;

import com.google.gson.annotations.SerializedName;

public class PostsModel {

    private int userId;
    private int id;
    private String title;
    @SerializedName("body")
    private String description;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

}
