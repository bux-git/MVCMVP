package com.example.mvp.view.impl;

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
import android.widget.Toast;

import com.example.mvp.R;
import com.example.mvp.adapter.RepositoryAdapter;
import com.example.mvp.model.Repository;
import com.example.mvp.presenter.impl.UserRepoImpl;
import com.example.mvp.view.UserReposBaseView;

import java.util.List;

import static com.example.mvp.R.id.button_search;
import static com.example.mvp.R.id.edit_text_username;
import static com.example.mvp.R.id.text_info;

/**
 * Description：
 * Author：Bux on 2017/10/5 12:56
 * Email: 471025316@qq.com
 */

public class UserRepoActivity extends AppCompatActivity implements View.OnClickListener,UserReposBaseView {

    private Toolbar mToolbar;
    private EditText mEtUserName;
    private ProgressBar mProgressBar;
    private TextView mTvInfo;
    private RecyclerView mRecyclerView;
    private RepositoryAdapter mRepositoryAdapter;
    private ImageButton mIBtnSearch;


    UserRepoImpl mUserRepo;
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

        mUserRepo = new UserRepoImpl();
        mUserRepo.attachView(this);

        mUserRepo.publicRepositories(username);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUserRepo.detachView();
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
                mUserRepo.publicRepositories(mEtUserName.getText().toString());
                break ;
        }
    }


    private void hideSoftKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEtUserName.getWindowToken(),0) ;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
        mTvInfo.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showErrorView() {
        mProgressBar.setVisibility(View.GONE);
        mTvInfo.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showRecyclerView(List<Repository> repos) {
        mProgressBar.setVisibility(View.GONE);
        mTvInfo.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);


        mRepositoryAdapter = new RepositoryAdapter(this,repos) ;
        mRecyclerView.setAdapter(mRepositoryAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager((this)));
    }

    @Override
    public void userNameIsEmpty() {
        Toast.makeText(this,"不能为空!",Toast.LENGTH_SHORT).show();
    }
}
