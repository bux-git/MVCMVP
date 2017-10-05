package com.example.mvp.presenter.impl;

import android.text.TextUtils;

import com.example.mvp.model.GithubService;
import com.example.mvp.model.Repository;
import com.example.mvp.presenter.UserRepoPresenter;
import com.example.mvp.view.UserReposBaseView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description：
 * Author：Bux on 2017/10/5 16:27
 * Email: 471025316@qq.com
 */

public class UserRepoImpl implements UserRepoPresenter {

    private UserReposBaseView mUserReposBaseView;
    private Subscription mSubscription;
    @Override
    public void attachView(UserReposBaseView userReposBaseView) {
        mUserReposBaseView=userReposBaseView;
    }

    @Override
    public void detachView() {
        mUserReposBaseView=null;
        if(mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void publicRepositories(String userName) {
        if(TextUtils.isEmpty(userName)){
            mUserReposBaseView.userNameIsEmpty();
            return;
        }
        mUserReposBaseView.showProgress();
        mSubscription= GithubService.Factory.create().publicRepositories(userName)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mUserReposBaseView.showErrorView();
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        mUserReposBaseView.showRecyclerView(repositories);
                    }
                });
    }
}
