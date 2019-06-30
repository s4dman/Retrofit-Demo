package com.droidman.gettingstartedretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsAPI {

    @GET("posts")
    Call<List<Post>> getPosts();
}
