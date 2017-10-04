package com.example.lym.mvcmvp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Description：
 * Author：Bux on 2017/10/4 15:47
 * Email: 471025316@qq.com
 */

public class PreferenceManager {

    /**
     * 根据Context获取SharedPreferences
     * @param context
     * @return
     */
    private static SharedPreferences getSharedPreferences(final Context context){
        return android.preference.PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * 判断是否是第一次
     * @param context
     * @param key
     * @return
     */
    public static boolean isFirstTime(Context context,String key){
        if(getBoolean(context,key ,false)){
            return false;
        }else{
            putBoolean(context, key, true);
            return true;
        }
    }

    /**
     * 是否包含KEY
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context,String key){
        return getSharedPreferences(context).contains(key);
    }


    public static int getInt(final Context context, final String key, final int defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getInt(key, defaultValue);
    }

    public static boolean putInt(final Context context, final String key, final int pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putInt(key, pValue);

        return editor.commit();
    }

    public static long getLong(final Context context, final String key, final long defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getLong(key, defaultValue);
    }

    public static Long getLong(final Context context, final String key, final Long defaultValue) {
        if (PreferenceManager.getSharedPreferences(context).contains(key)) {
            return PreferenceManager.getSharedPreferences(context).getLong(key, 0);
        } else {
            return null;
        }
    }


    public static boolean putLong(final Context context, final String key, final long pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putLong(key, pValue);

        return editor.commit();
    }

    public static boolean getBoolean(final Context context, final String key, final boolean defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static boolean putBoolean(final Context context, final String key, final boolean pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putBoolean(key, pValue);

        return editor.commit();
    }

    public static String getString(final Context context, final String key, final String defaultValue) {
        return PreferenceManager.getSharedPreferences(context).getString(key, defaultValue);
    }

    public static boolean putString(final Context context, final String key, final String pValue) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.putString(key, pValue);

        return editor.commit();
    }


    public static boolean remove(final Context context, final String key) {
        final SharedPreferences.Editor editor = PreferenceManager.getSharedPreferences(context).edit();

        editor.remove(key);

        return editor.commit();
    }
}
