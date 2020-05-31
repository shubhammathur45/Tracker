package com.example.employeetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.employeetracker.MainActivity;
import com.example.employeetracker.R;

/**
 * Created by aayu on 1/26/2017.
 */

public class FirstScreen extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstscreen);

    }
    public void adminLogin(View view)
    {
        startActivity(new Intent(FirstScreen.this, MainActivity.class));
        finish();
    }
    public void empLogin(View view)
    {
        startActivity(new Intent(FirstScreen.this,Employee_Login.class));
        finish();
    }

}
