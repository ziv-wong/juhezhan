package com.ziv.juhezhan.data;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.ref.WeakReference;

/**
 * @author Ziv
 * @date 2018.11.7
 * <p>
 * A class to use SharedPreferences conveniently.
 * MUST call init() BEFORE call other methods!
 * 一个方便使用SharedPreferences的类。
 * 调用其他方法前，请务必先调用init()方法！
 */

public class AppSaveDataSPUtil {

    //Main Catalog
    //主目录
    private static final String DATA_CONFIG = "data_config";
    //Set Something
    //设置可存取的配置
    private static final String SOMETHING   = "something";
    private static final String FIRST_IN    = "first_in";
    private static WeakReference<Context>   sContext;
    private static SharedPreferences        sSharedPreferences;
    private static SharedPreferences.Editor sEditor;

    private AppSaveDataSPUtil() {
    }

    //Must call this function before change or get the data
    //修改或获得数据前必须先调用该函数
    public static void init(Context context) {
        if (sContext != null) {
            sContext = null;
        }
        sContext = new WeakReference<>(context);
        sSharedPreferences = context.getSharedPreferences(DATA_CONFIG, Context.MODE_PRIVATE);
        sEditor = sSharedPreferences.edit();
        sEditor.commit();
    }

    public static boolean getSomething() {
        return sSharedPreferences.getBoolean(SOMETHING, true);
    }

    public static void setSomething(boolean something) {
        sEditor.putBoolean(SOMETHING, something);
        sEditor.commit();
    }

    public static boolean getIfFirstIn() {
        return sSharedPreferences.getBoolean(FIRST_IN, true);
    }

    public static void setFirstIn(boolean ifFirstIn) {
        sEditor.putBoolean(FIRST_IN, ifFirstIn);
        sEditor.commit();
    }
}
