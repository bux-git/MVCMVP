package com.example.mvp.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mvp.App;


/**
 * Description：
 * Author：Bux on 2017/10/4 15:47
 * Email: 471025316@qq.com
 */

public class PreferenceManager {

    /**
     * 根据Context获取SharedPreferences
     * @param fileName
     * @return
     */
    private static SharedPreferences getSharedPreferences(String fileName){
        return App.getContext().getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }

    /**
     * 判断是否是第一次
     * @param fileName
     * @param key
     * @return
     */
    public static boolean isFirstTime(String fileName,String key){
        if(getBoolean(fileName,key ,false)){
            return false;
        }else{
            putBoolean(fileName, key, true);
            return true;
        }
    }

    /**
     * 是否包含KEY
     * @param fileName
     * @param key
     * @return
     */
    public static boolean contains(String fileName,String key){
        return getSharedPreferences(fileName).contains(key);
    }


    public static int getInt(String fileName, final String key, final int defaultValue) {
        return PreferenceManager.getSharedPreferences(fileName).getInt(key, defaultValue);
    }

    public static boolean putInt(String fileName, final String key, final int pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(fileName).edit();

        editor.putInt(key, pValue);

        return editor.commit();
    }

    public static long getLong(String fileName, final String key, final long defaultValue) {
        return PreferenceManager.getSharedPreferences(fileName).getLong(key, defaultValue);
    }

    public static Long getLong(String fileName, final String key, final Long defaultValue) {
        if (PreferenceManager.getSharedPreferences(fileName).contains(key)) {
            return PreferenceManager.getSharedPreferences(fileName).getLong(key, 0);
        } else {
            return null;
        }
    }


    public static boolean putLong(String fileName, final String key, final long pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(fileName).edit();

        editor.putLong(key, pValue);

        return editor.commit();
    }

    public static boolean getBoolean(String fileName, final String key, final boolean defaultValue) {
        return PreferenceManager.getSharedPreferences(fileName).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(String fileName, final String key, final boolean pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(fileName).edit();

        editor.putBoolean(key, pValue);

        return editor.commit();
    }

    public static String getString(String fileName, final String key, final String defaultValue) {
        return PreferenceManager.getSharedPreferences(fileName).getString(key, defaultValue);
    }

    public static boolean putString(String fileName, final String key, final String pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(fileName).edit();

        editor.putString(key, pValue);

        return editor.commit();
    }


    public static boolean remove(String fileName, final String key) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(fileName).edit();

        editor.remove(key);

        return editor.commit();
    }
}
