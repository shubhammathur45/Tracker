package com.example.employeetracker.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.adapter.EmpTaskAdapter;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.EmpTaskListResponse;
import com.example.employeetracker.model.EmployeeTaskList;
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
public class EmpTaskList extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EmpTaskAdapter taskAdapter;
    List<EmployeeTaskList> employeeList;
    LoginResponse loginResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplist);
        employeeList=new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_employeelist);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Gson gson = new Gson();
        Log.d("AdminDetail", PrefUtil.getString(EmpTaskList.this,
                ApiParams.UPDATE_ADMIN, ApiParams.UPDATE_ADMIN));
        loginResponse = gson.fromJson(PrefUtil.getString(EmpTaskList.this,
                ApiParams.UPDATE_ADMIN, ApiParams.UPDATE_ADMIN), LoginResponse.class);
        getTaskeList();
    }

    private void getTaskeList() {
        ApiService apiService = ApiClient.getApiClient();
        Call<EmpTaskListResponse> call = apiService.empTaskListm("11");
        call.enqueue(new Callback<EmpTaskListResponse>() {


            @Override
            public void onResponse(Call<EmpTaskListResponse> call, Response<EmpTaskListResponse> response) {
                if (response.body().getMsg().equals("success")) {
                    employeeList=response.body().getTaskList();
                    taskAdapter=new EmpTaskAdapter(EmpTaskList.this,employeeList);

                    recyclerView.setAdapter(taskAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmpTaskListResponse> call, Throwable t) {

                Log.d("Failure", t.getMessage());
                Toast.makeText(EmpTaskList.this, "Sorry! Problem on Server", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void deleteTask(final com.example.employeetracker.model.TaskList taskList) {
        ProgressUtil.showDialog(EmpTaskList.this, "Deleting Task", "Please Wait");
        ApiService apiService = ApiClient.getApiClient();
        Call<ApiResponse> call = apiService.deleteTask(taskList.getId());
        call.enqueue(new Callback<ApiResponse>() {


            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ProgressUtil.hideDialog();

                if (response.body().getMsg().equals("success")) {

                    //employeeList.remove(taskList);
                    taskAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                ProgressUtil.hideDialog();
                Log.d("Failure", t.getMessage());
                Toast.makeText(EmpTaskList.this, "Sorry! Problem on Server", Toast.LENGTH_LONG).show();

            }
        });
    }


    private void showAlertDialog(String title, String msg, final com.example.employeetracker.model.TaskList taskList) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this).setTitle(title).setMessage(msg).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}

