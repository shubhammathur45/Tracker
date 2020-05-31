package com.example.employeetracker.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.employeetracker.R;

/**
 * Created by aayu on 1/5/2017.
 */
public class DateUtil {

   static Context context;


    public static void showDateAlert(Context cont, final Button textView)
    {
        context=cont;
        View view= LayoutInflater.from(context).inflate(R.layout.alert_datepicker,null);
        final DatePicker datePicker=(DatePicker)view.findViewById(R.id.datepicker);
        AlertDialog.Builder builder=new AlertDialog.Builder(context).setView(view).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuilder stringBuilder=new StringBuilder();
                stringBuilder.append(datePicker.getYear());
                stringBuilder.append("-");
                if(datePicker.getMonth()<10)
                {
                    stringBuilder.append("0"+String.valueOf(datePicker.getMonth()+1));
                }
                else
                {
                    stringBuilder.append(datePicker.getMonth()+1);
                }
                stringBuilder.append("-");
                if(datePicker.getDayOfMonth()<10)
                {
                    stringBuilder.append("0"+datePicker.getDayOfMonth());

                }
                else
                {
                    stringBuilder.append(datePicker.getDayOfMonth());
                }

                textView.setText(stringBuilder);

            }
        }).setTitle("Select Task Due Date");
        builder.create().show();


    }

}
