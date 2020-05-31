package com.example.employeetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.adapter.EmployeeAdapter;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.*;
import com.example.employeetracker.model.TaskList;
import com.example.employeetracker.util.DateUtil;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayu on 1/2/2017.
 */
public class CreateTask extends AppCompatActivity implements View.OnClickListener{


    TextInputEditText mTaskName,mLocation;
    Button mDate,mCreateTask;
    Spinner employee;
    LoginResponse loginResponse;
    List<String> employeNames;
    List<Employee>employeeList;
    Employee mEmployee;
    TaskList task;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createtask);
        loginResponse= new Gson().fromJson(PrefUtil.getString(CreateTask.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN),LoginResponse.class);
        initilize();
    }
    private void initilize()
    {
        mTaskName=(TextInputEditText)findViewById(R.id.et_tasktitle);
        mLocation=(TextInputEditText)findViewById(R.id.et_location);
        mDate=(Button) findViewById(R.id.tasklastdate);
        mCreateTask=(Button)findViewById(R.id.btn_createtask);
        mCreateTask.setOnClickListener(this);
        mDate.setOnClickListener(this);
        employee=(Spinner)findViewById(R.id.spin_employess);
        employee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    mEmployee=employeeList.get(i);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        getEmployeList();

    }
    private void createTask()
    {
        ProgressUtil.showDialog(CreateTask.this,"Creating Task","Please Wait...");
        ApiService apiService=ApiClient.getApiClient();
        Call<ApiResponse>call=apiService.createTask(mEmployee.getId(),
                mTaskName.getText().toString(),mLocation.getText().toString(),mDate.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ProgressUtil.hideDialog();
                Log.d("Response",response.body().getMsg());
                if(response.body().getMsg().equals("success"))
                {
                    Toast.makeText(CreateTask.this,"Task Successfully Created",Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(CreateTask.this,response.body().getMsg(),Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(CreateTask.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });
    }

    private void updateTask()
    {
        ProgressUtil.showDialog(CreateTask.this,"Updating  Task","Please Wait...");
        ApiService apiService=ApiClient.getApiClient();
        Call<ApiResponse>call=apiService.updateTask(task.getId(),mEmployee.getId(),
                mTaskName.getText().toString(),mLocation.getText().toString(),mDate.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                ProgressUtil.hideDialog();
                Log.d("Response",response.body().getMsg());
                if(response.body().getMsg().equals("success"))
                {
                    Toast.makeText(CreateTask.this,"Task Successfully Updated",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(CreateTask.this,TaskList.class));
                    finish();
                }
                else
                {
                    Toast.makeText(CreateTask.this,response.body().getMsg(),Toast.LENGTH_LONG).show();
                }


            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(CreateTask.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });
    }
    private  void getEmployeList()
    {

        employeNames=new ArrayList<>();
        employeeList=new ArrayList<>();
        ProgressUtil.showDialog(CreateTask.this,"Loading Data","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<EmployeApiResponse> call=apiService.getAllEmployee(loginResponse.getUid());
        call.enqueue(new Callback<EmployeApiResponse>() {


            @Override
            public void onResponse(Call<EmployeApiResponse> call, Response<EmployeApiResponse> response) {
                ProgressUtil.hideDialog();
                if (response.body().getMsg().equals("success")){
                    if(response.body().getEmployeeList().size()>0)
                    {
                        employeNames.add("Select Employee");
                        employeeList.add(new Employee());
                        for(int i=0;i<response.body().getEmployeeList().size();i++)
                        {
                            employeNames.add(response.body().getEmployeeList().get(i).getName());
                            employeeList.add(response.body().getEmployeeList().get(i));

                        }
                        ArrayAdapter<String>arrayAdapter=new ArrayAdapter<String>(CreateTask.this,android.R.layout.simple_spinner_dropdown_item,employeNames);
                        employee.setAdapter(arrayAdapter);
                        if(getIntent().getExtras()!=null)
                        {
                            task=getIntent().getExtras().getParcelable(ApiParams.EMPOLYEE_LOG);
                            mTaskName.setText(task.getTask());
                            mLocation.setText(task.getTaskLocation());
                            mDate.setText(task.getLastDate());
                            int postion=employeNames.indexOf(task.getEmpName());
                            employee.setSelection(postion);
                            mCreateTask.setText("Update Task");

                        }

                    }


                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                ProgressUtil.hideDialog();
                Toast.makeText(CreateTask.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
            }
        });
    }
    private boolean validate()
    {

        if(mTaskName.getText().toString().isEmpty())
        {
            mTaskName.requestFocus();
            mTaskName.setError("Please enter task name");
        }
        else if(mLocation.getText().toString().isEmpty())
        {
            mLocation.requestFocus();
            mLocation.setError("Please enter location");
        }
        if(!mDate.getText().toString().contains("-"))
        {
            mDate.requestFocus();
            mDate.setError("Please Select Valid Date");
        }
        if(employee.getSelectedItem().toString().equals("Select Employee"))
        {
            Toast.makeText(CreateTask.this,"Please Select Employee",Toast.LENGTH_LONG).show();

        }
        else
        {
            return true;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.btn_createtask:

                if(validate())
                {
                    if(mCreateTask.getText().toString().equals("Update Task"))
                    {
                        updateTask();
                    }
                    else
                    {
                        createTask();
                    }

                }
                break;
            case R.id.tasklastdate:
                DateUtil.showDateAlert(CreateTask.this,mDate);
                break;
        }

    }
}
