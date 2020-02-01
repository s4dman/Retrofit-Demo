package com.droidman.gettingstartedretrofit.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.droidman.gettingstartedretrofit.Helper;
import com.droidman.gettingstartedretrofit.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteFragment extends Fragment {
    private static final String TAG = "DeleteFragment";

    private EditText mPostId;
    private TextView mResponseCode;
    private Button mDeleteBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Delete");
        View view = inflater.inflate(R.layout.fragment_delete, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPostId = view.findViewById(R.id.delete_post_id);
        mResponseCode = view.findViewById(R.id.delete_response_code);
        mDeleteBtn = view.findViewById(R.id.btn_delete);

        mDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePost(Integer.parseInt(mPostId.getText().toString()));
            }
        });
    }


    private void deletePost(int postId) {
        Call<Void> deleteCall = Helper.getPostsAPI().deletePost(postId);

        deleteCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                String responseCode = String.valueOf(response.code());
                mResponseCode.setText("Response code: " + responseCode);
                Toast.makeText(getContext(), "Deleted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });


    }
}