package com.example.mvp.presenter;

import com.example.mvp.view.BaseView;

/**
 * Description：
 * Author：Bux on 2017/10/5 14:13
 * Email: 471025316@qq.com
 */

public interface BasePresenter<T extends BaseView> {
    void attachView(T t);

    void detachView();
}
