package com.example.employeetracker.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.adapter.EmployeeAdapter;
import com.example.employeetracker.adapter.TaskAdapter;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.model.AdminTaskList;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.EmployeApiResponse;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.LoginResponse;
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
public class TaskList extends AppCompatActivity  implements ItemUpdateAndDeleteListener{



    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    TaskAdapter taskAdapter;
    List<com.example.employeetracker.model.TaskList> employeeList;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplist);
        employeeList=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.recycler_employeelist);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        Gson gson=new Gson();
        Log.d("AdminDetail", PrefUtil.getString(TaskList.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN));
        loginResponse= gson.fromJson(PrefUtil.getString(TaskList.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN),LoginResponse.class);
        getTaskeList();
    }
    private  void getTaskeList()
    {
        ApiService apiService= ApiClient.getApiClient();
        Call<AdminTaskList> call=apiService.getAllTasks(loginResponse.getUid());
        call.enqueue(new Callback<AdminTaskList>() {


            @Override
            public void onResponse(Call<AdminTaskList> call, Response<AdminTaskList> response) {
                if (response.body().getMsg().equals("success")){
                    employeeList=response.body().getTaskList();
                    taskAdapter=new TaskAdapter(TaskList.this,employeeList);

                    recyclerView.setAdapter(taskAdapter);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<AdminTaskList> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(TaskList.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
            }
        });
    }
    private  void deleteTask(final com.example.employeetracker.model.TaskList taskList)
    {
        ProgressUtil.showDialog(TaskList.this,"Deleting Task","Please Wait");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse> call=apiService.deleteTask(taskList.getId());
        call.enqueue(new Callback<ApiResponse>() {


            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ProgressUtil.hideDialog();

                if (response.body().getMsg().equals("success")){

                    employeeList.remove(taskList);
                    taskAdapter.notifyDataSetChanged();
                                   }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ProgressUtil.hideDialog();
                Log.d("Failure",t.getMessage());
                Toast.makeText(TaskList.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();

            }
        });
    }


    @Override
    public void deleteItem(int position) {
        com.example.employeetracker.model.TaskList tasklist=employeeList.get(position);
        Log.d("Task Id",tasklist.getId());
        showAlertDialog("Warning","Do you want to Delete this Task",tasklist);

    }

    @Override
    public void updateItem(int position) {
        Intent intent=new Intent(TaskList.this,CreateTask.class);
        intent.putExtra(ApiParams.EMPOLYEE_LOG,employeeList.get(position));
        startActivity(intent);
        finish();
    }

    private void showAlertDialog(String title, String msg, final com.example.employeetracker.model.TaskList taskList)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle(title).setMessage(msg).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteTask(taskList);

            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
}

