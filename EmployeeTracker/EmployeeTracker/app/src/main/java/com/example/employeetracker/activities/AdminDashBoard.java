package com.example.employeetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.employeetracker.R;
import com.example.employeetracker.api.ApiParams;

/**
 * Created by aayu on 1/2/2017.
 */
public class AdminDashBoard extends AppCompatActivity implements View.OnClickListener{


    Button mCreateTask,mCreateEmployee,mViewTasks,mViewEmployee,profile,logs,logout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admindashboard);
        mCreateTask=(Button)findViewById(R.id.bt_createtask);
        mCreateEmployee=(Button)findViewById(R.id.bt_createmployee);
        mViewTasks=(Button)findViewById(R.id.bt_viewtasks);
        mViewEmployee=(Button)findViewById(R.id.bt_viewemployees);
        profile=(Button)findViewById(R.id.bt_profile);
        logs=(Button)findViewById(R.id.bt_logs);
        mCreateEmployee.setOnClickListener(this);
        mCreateTask.setOnClickListener(this);
        mViewTasks.setOnClickListener(this);
        mViewEmployee.setOnClickListener(this);
        profile.setOnClickListener(this);
        logs.setOnClickListener(this);
        logout=(Button)findViewById(R.id.bt_logout);
        logout.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
            switch (view.getId())
            {
                case R.id.bt_createmployee:
                intent=new Intent(AdminDashBoard.this,AddEmployee.class);
                    break;
                case R.id.bt_createtask:
                    intent=new Intent(AdminDashBoard.this,CreateTask.class);
                    break;

                case R.id.bt_viewemployees:

                    intent=new Intent(AdminDashBoard.this,EmployeeList.class);
                    intent.putExtra(ApiParams.KEY_FLAG,"1");
                    break;
                case R.id.bt_viewtasks:
                    intent=new Intent(AdminDashBoard.this,TaskList.class);
                    break;
                case R.id.bt_profile:
                    intent=new Intent(AdminDashBoard.this,UpdateAdminProfile.class);
                    break;
                case R.id.bt_logs:
                    intent=new Intent(AdminDashBoard.this,EmployeeList.class);
                    intent.putExtra(ApiParams.KEY_FLAG,"2");
                    break;
                case R.id.bt_logout:
                    startActivity(new Intent(AdminDashBoard.this,FirstScreen.class));
                    finish();
                    break;

            }
        if(intent!=null)
        {
            startActivity(intent);
        }
    }
}
