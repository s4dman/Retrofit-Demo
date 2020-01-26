package com.droidman.gettingstartedretrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Helper {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static PostsAPI postsAPI = retrofit.create(PostsAPI.class); // The Retrofit class generates an implementation of the PostsAPI interface.

    public static PostsAPI getPostsAPI() {
        return postsAPI;
    }
}
