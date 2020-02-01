package com.droidman.gettingstartedretrofit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidman.gettingstartedretrofit.Helper;
import com.droidman.gettingstartedretrofit.PostsModel;
import com.droidman.gettingstartedretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {
    private static final String TAG = "PostFragment";
    private TextView mResponseCode, mUserId, mPostTitle, mPostDescription;
    private EditText mUid, mPtitle, mPdescription;
    private Button mPostBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("POST");
        View view = inflater.inflate(R.layout.fragment_post, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mResponseCode = view.findViewById(R.id.text_response_code);
        mUserId = view.findViewById(R.id.text_user_id);
        mPostTitle = view.findViewById(R.id.text_post_title);
        mPostDescription = view.findViewById(R.id.text_post_description);
        mUid = view.findViewById(R.id.edit_user_id);
        mPtitle = view.findViewById(R.id.edit_post_title);
        mPdescription = view.findViewById(R.id.edit_post_description);
        mPostBtn = view.findViewById(R.id.button_post);

        mPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userId = String.valueOf(mUid.getText());
                String postTitle = String.valueOf(mPtitle.getText());
                String postDesc = String.valueOf(mPdescription.getText());

                createPost(userId, postTitle, postDesc);
            }
        });
    }

    private void createPost(String userId, String postTitle, String postDesc) {


        PostsModel postsModel = new PostsModel(userId, postTitle, postDesc);

        Call<PostsModel> postsCall = Helper.getPostsAPI().createPost(postsModel);
        postsCall.enqueue(new Callback<PostsModel>() {
            @Override
            public void onResponse(Call<PostsModel> call, Response<PostsModel> response) {

                Toast.makeText(getContext(), "Post Success", Toast.LENGTH_LONG).show();

                String responseCode = String.valueOf(response.code());
                mResponseCode.setText("Response code: \n" + responseCode);
                mUserId.setText("User id: \n" + response.body().getUserId());
                mPostTitle.setText("Post title: \n" + response.body().getTitle());
                mPostDescription.setText("Post Description: \n" + response.body().getDescription());
            }

            @Override
            public void onFailure(Call<PostsModel> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });
    }
}