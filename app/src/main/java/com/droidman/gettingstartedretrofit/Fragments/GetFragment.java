package com.droidman.gettingstartedretrofit.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.droidman.gettingstartedretrofit.Adapters.PostsAdapter;
import com.droidman.gettingstartedretrofit.Helper;
import com.droidman.gettingstartedretrofit.PostsModel;
import com.droidman.gettingstartedretrofit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetFragment extends Fragment {

    private static String TAG = "GET_POSTS_FRAGMENT";
    private Context mContext;
    private List<PostsModel> mPostsModelList;
    private PostsModel mPostsModel;
    private RecyclerView mPostRecyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getActivity().setTitle("GET");
        View view = inflater.inflate(R.layout.fragment_get, container, false);
        mPostRecyclerView = view.findViewById(R.id.recycler_posts);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPosts();
    }

    private void getPosts() {
        Call<List<PostsModel>> postsCall = Helper.getPostsAPI().getPosts();

        postsCall.enqueue(new Callback<List<PostsModel>>() {
            @Override
            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mPostsModelList = response.body();
                    Log.d(TAG, "onResponse: " + mPostsModelList.toString());
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
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        mPostRecyclerView.setLayoutManager(linearLayoutManager);
        PostsAdapter postsAdapter = new PostsAdapter(mPostsModelList, mContext);
        mPostRecyclerView.setAdapter(postsAdapter);
    }
}
