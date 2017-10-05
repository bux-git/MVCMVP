package com.example.mvp.presenter.impl;

import com.example.mvp.constant.Constant;
import com.example.mvp.model.GithubService;
import com.example.mvp.model.Repo;
import com.example.mvp.presenter.MainPresenter;
import com.example.mvp.view.MainBaseView;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Description：
 * Author：Bux on 2017/10/5 14:35
 * Email: 471025316@qq.com
 */

public class MainPresenterImpl implements MainPresenter {

    private MainBaseView mMainBaseView;
    private Subscription mSubscription;

    @Override
    public void attachView(MainBaseView mainBaseView) {
        mMainBaseView = mainBaseView;
    }

    @Override
    public void detachView() {
        mMainBaseView = null;
        if(mSubscription.isUnsubscribed()){
            mSubscription.unsubscribe();
        }
    }

    @Override
    public void loadJavaGithub() {
        mMainBaseView.showProgress();
        mSubscription=  GithubService.Factory.create().javaRepositories(Constant.JAVA_REPO_URL)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Repo>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainBaseView.showErrorView();
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        mMainBaseView.showRecyclerView(repos);
                    }
                });
    }
}
