package com.milesilac.whackemoji;

/*
    SharedPrefs Singleton
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    private static SharedPreferences sharedPrefs;
    public static final String HIGHSCORETIMED = "highScoreTimed";
    public static final String HIGHSCOREUNTIMED = "highScoreUntimed";

    private SharedPrefs() {
    }

    public static void init(Context context) {
        if (sharedPrefs == null) {
            sharedPrefs = context.getSharedPreferences(context.getPackageName(), Activity.MODE_PRIVATE);
        }
    }

    public static String read(String key, String defValue) {
        return sharedPrefs.getString(key, defValue);
    }

    public static void write(String key, String value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(key, value);
        editor.apply();
    }


    public static Integer read(String key, int defValue) {
        return sharedPrefs.getInt(key, defValue);
    }

    public static void write(String key, Integer value) {
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }
} //SharedPrefs
