package com.example.potik.mvvmpattern;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.example.potik.mvvmpattern.data.Post;
import com.example.potik.mvvmpattern.data.ViewModelStatus;
import com.example.potik.mvvmpattern.manager.ApiManager;

import java.util.ArrayList;
import java.util.List;

public class PostListViewModel extends ViewModel {

    private MutableLiveData<List<Post>> postsData;
    private MutableLiveData<ViewModelStatus> status = new MutableLiveData<>();
    private ViewModelStatus statusData = new ViewModelStatus();

    public LiveData<ViewModelStatus> getStatus(){
        return status;
    }

    public LiveData<List<Post>> getPosts() {
        if (postsData == null){
            postsData = new MutableLiveData<>();
            loadPosts();
        }
        return postsData;
    }

    private void loadPosts() {
        statusData.isLoadingList = true;
        status.setValue(statusData);
        ApiManager.sInstance.getPosts(new ApiManager.GetPostsCallback() {
            @Override
            public void onResponse(ArrayList<Post> posts) {
                postsData.setValue(posts);
                statusData.isLoadingList = false;
                status.setValue(statusData);
            }
        });
    }

}
