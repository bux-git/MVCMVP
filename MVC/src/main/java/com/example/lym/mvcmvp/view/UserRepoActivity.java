package com.example.lym.mvcmvp.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.lym.mvcmvp.R;
import com.example.lym.mvcmvp.adapter.RepositoryAdapter;
import com.example.lym.mvcmvp.model.GithubService;
import com.example.lym.mvcmvp.model.Repository;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.example.lym.mvcmvp.R.id.button_search;
import static com.example.lym.mvcmvp.R.id.edit_text_username;
import static com.example.lym.mvcmvp.R.id.text_info;

/**
 * Description：
 * Author：Bux on 2017/10/5 12:56
 * Email: 471025316@qq.com
 */

public class UserRepoActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private EditText mEtUserName;
    private ProgressBar mProgressBar;
    private TextView mTvInfo;
    private RecyclerView mRecyclerView;
    private RepositoryAdapter mRepositoryAdapter;
    private ImageButton mIBtnSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_repo);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mEtUserName = (EditText) findViewById(edit_text_username);
        mProgressBar = (ProgressBar) findViewById(R.id.progress);
        mTvInfo = (TextView) findViewById(text_info);
        mRecyclerView = (RecyclerView) findViewById(R.id.repos_recycler_view);
        mIBtnSearch = (ImageButton) findViewById(button_search);

        setSupportActionBar(mToolbar);
        addTextListener();
        mIBtnSearch.setOnClickListener(this);

        String username = getIntent().getStringExtra("username");
        mEtUserName.setText(username);
        loadGithubRepos(username) ;
    }

    private void loadGithubRepos(String username) {
        GithubService.Factory.create().publicRepositories(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                        mTvInfo.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mProgressBar.setVisibility(View.GONE);
                        mTvInfo.setVisibility(View.VISIBLE);
                        mRecyclerView.setVisibility(View.GONE);
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        mProgressBar.setVisibility(View.GONE);
                        if(repositories != null){
                            mRecyclerView.setVisibility(View.VISIBLE);
                            setupRecyclerView(mRecyclerView,repositories);
                        }
                    }
                });
    }

    private void setupRecyclerView(RecyclerView recyclerView,List<Repository> repos) {
        mRepositoryAdapter = new RepositoryAdapter(this,repos) ;
        recyclerView.setAdapter(mRepositoryAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    private void addTextListener() {
        mEtUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mIBtnSearch.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_search:
                hideSoftKeyboard();
                mTvInfo.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
                loadGithubRepos(mEtUserName.getText().toString()) ;
                break ;
        }
    }


    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtUserName.getWindowToken(),0) ;
    }
}
