package com.example.employeetracker.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by aayu on 12/24/2016.
 */
public class NetUtil {

    public static boolean checkNetwork(Context context)
    {
        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo.isConnected() && networkInfo.isAvailable())
        {
            return true;
        }

        return false;

        
    }
}
