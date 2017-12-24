package com.example.tjh.myapplication.engin;

/**
 * Created by TJH on 2017/12/5.
 */

public class JNI {
    static {
        System.loadLibrary("native-lib");
    }
    public static native String getAppKey();
}
