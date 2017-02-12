package com.rakesh.hashkeynretrofit;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Admin on 11-02-2017.
 */
public class AppSingleton {
    private static AppSingleton ourInstance = new AppSingleton();

    public static AppSingleton getInstance() {
        return ourInstance;
    }

    private AppSingleton() {
    }

    public static void sSavePreferences(Context context, String key, String value) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String sLoadSavedPreferencesString(Context context, String key) {
        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context);

        String value = sharedPreferences.getString(key, "No Value Found");
        return value;
    }

}
