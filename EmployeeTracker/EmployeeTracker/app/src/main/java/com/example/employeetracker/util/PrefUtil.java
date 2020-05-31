package com.example.employeetracker.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

/**
 * Created by aayu on 12/24/2016.
 */
public class PrefUtil {

    public static void putString(Context context,String fileName,String key,String value)
    {
        SharedPreferences preference=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preference.edit();
        editor.putString(key,value);
        editor.commit();

    }
    public static String getString(Context context,String fileName,String key)
    {
        SharedPreferences preference=context.getSharedPreferences(fileName,Context.MODE_PRIVATE);
        return preference.getString(key,null);

    }
}

