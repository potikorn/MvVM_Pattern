package com.example.potik.mvvmpattern.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.potik.mvvmpattern.PostDetailActivity;
import com.example.potik.mvvmpattern.R;
import com.example.potik.mvvmpattern.data.Post;

import java.util.List;

public class PostsRecyclerViewAdapter extends RecyclerView.Adapter<PostsRecyclerViewAdapter.ViewHolder> {

    private final List<Post> mPosts;

    public PostsRecyclerViewAdapter(List<Post> posts) {
        mPosts = posts;
    }

    @Override
    public PostsRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_content, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PostsRecyclerViewAdapter.ViewHolder viewHolder, int i) {
        Post post = mPosts.get(i);
        viewHolder.setContent(post);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mImageView;
        final TextView mIdView;
        final TextView mContentView;
        Post mPost;

        ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
            mImageView = (ImageView) view.findViewById(R.id.image);
        }

        void setContent(Post post) {
            mPost = post;
            mIdView.setText(mPost.postId);
            mContentView.setText(mPost.title);
            Glide.with(mImageView).load(mPost.imageUrl).into(mImageView);
            mView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), PostDetailActivity.class);
                intent.putExtra(PostDetailActivity.ARG_POST_ID, mPost.postId);
                v.getContext().startActivity(intent);
            });
        }

    }


}
