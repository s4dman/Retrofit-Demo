package com.droidman.gettingstartedretrofit;

import com.google.gson.annotations.SerializedName;

public class PostsModel {

    private String userId;
    private Integer id;
    private String title;
    @SerializedName("body")
    private String description;

    public PostsModel(String userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }

    public String getUserId() {
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
