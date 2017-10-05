package com.example.mvp.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.mvp.R;
import com.example.mvp.adapter.RepoListAdapter;
import com.example.mvp.model.Repo;
import com.example.mvp.presenter.impl.MainPresenterImpl;
import com.example.mvp.view.MainBaseView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainBaseView {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private RepoListAdapter mRepoListAdapter;
    private TextView mTvDescription;
    private ProgressBar mProgressBar;
    private TextView mTvInfo;

    private MainPresenterImpl mMainPresenter;

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
        mMainPresenter = new MainPresenterImpl();
        mMainPresenter.attachView(this);
        mMainPresenter.loadJavaGithub();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMainPresenter.detachView();
    }


    @Override
    public void showProgress() {
        mTvInfo.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mTvInfo.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repo> repos) {
        mTvInfo.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);

        mRepoListAdapter = new RepoListAdapter(this,repos);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRepoListAdapter);
    }
}
