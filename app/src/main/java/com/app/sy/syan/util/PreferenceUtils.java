package com.app.sy.syan.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Set;

/**
 * Created by HXD on 2017/7/26 0026.
 *   选择工具
 */
public class PreferenceUtils {
    public static String getPrefString(Context context, String key, String defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getString(key, defaultValue);
    }

    public static void setPrefString(Context context, String key, String value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putString(key, value).apply();
    }

    public static boolean getPrefBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getBoolean(key, defaultValue);
    }

    public static boolean hasKey(Context context, String key) {
        return PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext()).contains(key);
    }

    public static void setPrefBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putBoolean(key, value).apply();
    }

    public static void setPrefInt(Context context, String key, int value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putInt(key, value).apply();
    }

    public static int getPrefInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getInt(key, defaultValue);
    }

    public static void setPrefFloat(Context context, String key, float value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putFloat(key, value).apply();
    }

    public static float getPrefFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getFloat(key, defaultValue);
    }

    public static void setPrefLong(Context context, String key, long value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putLong(key, value).apply();
    }

    public static long getPrefLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getLong(key, defaultValue);
    }

    public static void setPrefSet(Context context, String key, Set value) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        settings.edit().putStringSet(key, value).apply();
    }

    public static Set getPrefSet(Context context, String key, Set defaultValue) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.getStringSet(key, defaultValue);
    }

    public static boolean contains(Context context, String key) {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        return settings.contains(key);
    }

    public static void clearPreference(Context context, SharedPreferences p) {
        SharedPreferences.Editor editor = p.edit();
        editor.clear();
        editor.apply();
    }
}
