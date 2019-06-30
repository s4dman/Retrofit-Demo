package com.droidman.gettingstartedretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResult = findViewById(R.id.result);
        posts();
        users();
    }

    private void posts() {
        Call<List<PostsModel>> postsCall = Helper.getPostAPI().getPosts(); //Each Call from the created PostsAPI can make a synchronous or asynchronous HTTP request to the remote webservice.

        postsCall.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {

                    List<PostsModel> postsModelList = response.body();
                    for (PostsModel postsModel : postsModelList) {
                        String content = "";
                        content += "ID: " + postsModel.getId() + "\n";
                        content += "Title: " + postsModel.getTitle() + "\n";
                        content += "Description: " + postsModel.getDescription() + "\n\n";

                        mResult.append(content);

                    }

                }
            }

            @Override
            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
            }
        });
    }
    private void users(){
        final Call<List<UsersModel>> usersCall = Helper.getPostAPI().getUsers();

        usersCall.enqueue(new Callback<List<UsersModel>>() {
            @Override
            public void onResponse(Call<List<UsersModel>> call, Response<List<UsersModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UsersModel> usersModelList = response.body();
                    for (UsersModel usersModel : usersModelList) {
                        Log.d(TAG, "onResponse: " + usersModel.getId() + " " + usersModel.getName() + " " + usersModel.getEmail() );
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UsersModel>> call, Throwable throwable) {

            }
        });
    }
}
