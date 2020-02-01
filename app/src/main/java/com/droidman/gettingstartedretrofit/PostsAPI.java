package com.droidman.gettingstartedretrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostsAPI {

    @GET("posts")
    Call<List<PostsModel>> getPosts();

    @POST("posts")
    Call<PostsModel> createPost(@Body PostsModel postsModel);

    @DELETE("posts/{id}")
    Call<Void> deletePost(@Path("id") int id);
}
