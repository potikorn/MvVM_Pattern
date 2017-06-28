package com.example.potik.mvvmpattern;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.potik.mvvmpattern.adapter.PostsRecyclerViewAdapter;
import com.example.potik.mvvmpattern.base.BaseActivity;
import com.example.potik.mvvmpattern.data.Post;

import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_list);
        initView();
        PostListViewModel viewModel = ViewModelProviders.of(this).get(PostListViewModel.class);
        viewModel.getPosts().observe(this, this::displayPosts);
        viewModel.getStatus().observe(this, status -> {
            if (status != null && status.isLoadingList)
                showLoading();
            else
                hideLoading();
        });

    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), PostCreateActivity.class);
                startActivity(intent);
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.item_list);
    }

    private void displayPosts(List<Post> posts) {
        mRecyclerView.setAdapter(new PostsRecyclerViewAdapter(posts));
    }
}
