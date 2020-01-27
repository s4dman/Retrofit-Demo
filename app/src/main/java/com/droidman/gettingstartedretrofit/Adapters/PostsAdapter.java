package com.droidman.gettingstartedretrofit.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.droidman.gettingstartedretrofit.PostsModel;
import com.droidman.gettingstartedretrofit.R;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    private List<PostsModel> mPostsModelList;
    private Context mContext;

    public PostsAdapter(List<PostsModel> mPostsModelList, Context mContext) {
        this.mPostsModelList = mPostsModelList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_post, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder viewHolder, int i) {
        viewHolder.mTitle.setText(mPostsModelList.get(i).getTitle());
        viewHolder.mDescription.setText(mPostsModelList.get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return mPostsModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle, mDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.post_title);
            mDescription = itemView.findViewById(R.id.post_details);
        }
    }
}
