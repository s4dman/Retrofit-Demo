package com.droidman.gettingstartedretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.droidman.gettingstartedretrofit.Adapter.PostsAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private int userId;
    private List<PostsModel> mPostsModelList;
    private PostsModel mPostsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        posts();

    }

    private void posts() {
        //Each Call from the created PostsAPI can make a synchronous or asynchronous HTTP request to the remote webservice.
        final Call<List<PostsModel>> postsCall = Helper.getPostsAPI().getPosts();

        postsCall.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mPostsModelList = response.body();
                    initPostsRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
            }
        });
    }

    private void initPostsRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler_posts);
        recyclerView.setLayoutManager(linearLayoutManager);
        PostsAdapter postsAdapter = new PostsAdapter(mPostsModelList, this);
        recyclerView.setAdapter(postsAdapter);
    }
}
