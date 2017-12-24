package com.example.tjh.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by TJH on 2017/11/30.
 */

public class PreUtils {
    public static final String PREF_NAME = "news";
    public static SharedPreferences sp;
    public static void putBoolean(Context ctx,String key,boolean value){
        if(sp == null){
            sp = ctx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key,value).apply();
    }
    public static boolean getBoolean(Context ctx,String key,boolean defValue){
        if(sp == null){
            sp = ctx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key,defValue);
    }
    public static void putString(Context ctx,String key,String value){
        if(sp == null){
            sp = ctx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }
        sp.edit().putString(key,value).apply();
    }
    public static String getString(Context ctx,String key,String defValue){
        if(sp == null){
            sp = ctx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        }
        return sp.getString(key,defValue);
    }
}
