package com.example.lym.mvcmvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lym.mvcmvp.R;
import com.example.lym.mvcmvp.adapter.RepoListAdapter;
import com.example.lym.mvcmvp.constant.Constant;
import com.example.lym.mvcmvp.model.GithubService;
import com.example.lym.mvcmvp.model.Repo;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RepoListAdapter mRepoListAdapter;
    private TextView mTvDescription;
    private ProgressBar mProgressBar;
    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mTvDescription = (TextView) findViewById(R.id.text_description);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTvInfo = (TextView) findViewById(R.id.text_info);

        setSupportActionBar(mToolbar);
        mTvDescription.setText("GitHub Java");
        loadGitHubRepo();
    }

    private void loadGitHubRepo() {
        GithubService.Factory.create().javaRepositories(Constant.JAVA_REPO_URL)
                .observeOn(AndroidSchedulers.mainThread())//观察者主线程中运行
                .subscribeOn(Schedulers.io())//被观察者在子线程中运行
                .subscribe(new Observer<List<Repo>>() {


                    @Override
                    public void onCompleted() {
                        mTvInfo.setVisibility(View.GONE);
                        mProgressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                        mTvInfo.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        mTvInfo.setVisibility(View.GONE);
                        mProgressBar.setVisibility(View.GONE);
                        mRecyclerView.setVisibility(View.VISIBLE);
                        if(repos!=null){
                            setupRecyclerView(repos);
                        }
                    }
                });

    }

    private void setupRecyclerView(List<Repo> repos) {
        mRepoListAdapter = new RepoListAdapter(this,repos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRepoListAdapter);
    }
}
