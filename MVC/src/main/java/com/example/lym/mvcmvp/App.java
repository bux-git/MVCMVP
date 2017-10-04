package com.example.lym.mvcmvp;

import android.app.Application;

import com.example.lym.mvcmvp.util.FavoReposHelper;

/**
 * Description：
 * Author：Bux on 2017/10/4 15:28
 * Email: 471025316@qq.com
 */

public class App extends Application {

    private static App context;

    public static Application getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
        FavoReposHelper.init(this);
    }


}
