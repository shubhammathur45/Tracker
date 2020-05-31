package com.example.employeetracker.util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by aayu on 12/24/2016.
 */
public class ProgressUtil {

    public static ProgressDialog mProgressDialog;
    public static void showDialog(Context context,String title,String msg)
    {
        mProgressDialog=new ProgressDialog(context);
        mProgressDialog.setTitle(title);
        mProgressDialog.setMessage(msg);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.show();
    }
    public static void hideDialog()
    {
        if(mProgressDialog.isShowing()&&mProgressDialog!=null)
        {
            mProgressDialog.dismiss();
        }
    }
}
