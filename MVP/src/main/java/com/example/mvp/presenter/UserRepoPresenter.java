package com.example.mvp.presenter;

import com.example.mvp.view.UserReposBaseView;

/**
 * Description：
 * Author：Bux on 2017/10/5 16:23
 * Email: 471025316@qq.com
 */

public interface UserRepoPresenter extends BasePresenter<UserReposBaseView> {

    void publicRepositories(String userName);
}
