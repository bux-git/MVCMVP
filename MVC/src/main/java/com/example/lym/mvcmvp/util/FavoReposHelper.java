package com.example.lym.mvcmvp.util;

import android.app.Application;
import android.content.Context;

import com.example.lym.mvcmvp.model.Repo;

/**
 * Description：
 * Author：Bux on 2017/10/4 16:39
 * Email: 471025316@qq.com
 */

public class FavoReposHelper {

    final static String FILE_NAME="FavoRepos";

    static FavoReposHelper sHelper;
    String mPrefKey = "favo_repos";
    String favoReposJSON = "";
    Context mContext;

    private FavoReposHelper(Context context){
        mContext = context;
        favoReposJSON = PreferenceManager.getString(FILE_NAME, mPrefKey, "");
    }

    public static  void init(Application context){
        sHelper = new FavoReposHelper(context);
    }

    public static synchronized FavoReposHelper getInstance(){
        return sHelper;
    }

    public boolean contains(Repo repo){
        if(repo!=null){
            return favoReposJSON.contains(repo.getHref());
        }
        return false;
    }

    public void addFavo(Repo repo){
        favoReposJSON = favoReposJSON + "" + repo.getHref();
        saveToPref();
    }

    public void removeFavo(Repo repo){
        favoReposJSON = favoReposJSON.replace(repo.getHref(), "");
        saveToPref();
    }

    private void saveToPref() {
        PreferenceManager.putString(FILE_NAME, mPrefKey, favoReposJSON);
    }
}
