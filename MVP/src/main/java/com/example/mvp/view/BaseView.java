package com.example.mvp.view;

import java.util.List;

/**
 * Description：
 * Author：Bux on 2017/10/5 14:28
 * Email: 471025316@qq.com
 */

public interface BaseView<T> {
    void showProgress();

    void showErrorView();

    void showRecyclerView(List<T> tList);
}
