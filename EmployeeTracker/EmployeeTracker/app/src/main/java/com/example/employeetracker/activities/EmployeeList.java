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
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
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
public class EmployeeList extends AppCompatActivity  implements ItemUpdateAndDeleteListener{


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EmployeeAdapter employeeAdapter;
    List<Employee> employeeList;
    LoginResponse loginResponse;
    int selectedPostion;
    String flag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplist);
        employeeList=new ArrayList<>();
        if(getIntent().getExtras()!=null)
        {
            flag=getIntent().getExtras().getString(ApiParams.KEY_FLAG);
        }

        recyclerView=(RecyclerView)findViewById(R.id.recycler_employeelist);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        Gson gson=new Gson();
       Log.d("AdminDetail",PrefUtil.getString(EmployeeList.this,
               ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN));
         loginResponse= gson.fromJson(PrefUtil.getString(EmployeeList.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN),LoginResponse.class);
       getEmployeList();
    }
    private  void getEmployeList()
    {

        ProgressUtil.showDialog(EmployeeList.this,"Loading Employeess","Please Wait....");
        ApiService apiService= ApiClient.getApiClient();
        Call<EmployeApiResponse>call=apiService.getAllEmployee(loginResponse.getUid());
        call.enqueue(new Callback<EmployeApiResponse>() {


            @Override
            public void onResponse(Call<EmployeApiResponse> call, Response<EmployeApiResponse> response) {
                ProgressUtil.hideDialog();
                if (response.body().getMsg().equals("success")){
                    employeeList=response.body().getEmployeeList();
                    employeeAdapter=new EmployeeAdapter(EmployeeList.this,employeeList,flag);

                    recyclerView.setAdapter(employeeAdapter);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeApiResponse> call, Throwable t) {
                ProgressUtil.hideDialog();

                Log.d("Failure",t.getMessage());
                Toast.makeText(EmployeeList.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
            }
        });
    }

    private  void deleteEmpolyee(final Employee employee)
    {

        ProgressUtil.showDialog(EmployeeList.this,"Deleting Employee","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse>call=apiService.deleteEmployee(employee.getId());
        call.enqueue(new Callback<ApiResponse>() {


            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ProgressUtil.hideDialog();
                if (response.body().getMsg().equals("success")){
                    {
                        employeeList.remove(employee);
                        employeeAdapter.notifyDataSetChanged();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(EmployeeList.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });
    }

    @Override
    public void deleteItem(int position) {
        Employee employee=employeeList.get(position);
        showAlertDialog("Warning","Do your really Want to Delete Employee?",employee);


    }


    private void showAlertDialog(String title, String msg, final Employee employee)
    {

        AlertDialog.Builder builder=new AlertDialog.Builder(this).setTitle(title).setMessage(msg).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteEmpolyee(employee);
                dialogInterface.dismiss();

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

    @Override
    public void updateItem(int position) {

        if(flag.equals("1"))
        {
            Intent intent=new Intent(this,AddEmployee.class);
            intent.putExtra(ApiParams.KEY_FLAG,1);
            intent.putExtra(ApiParams.KEY_EMP_ID,employeeList.get(position));
            startActivity(intent);
            finish();

        }
        else
        {
            Intent intent=new Intent(this,EmployeeLogs.class);
            startActivity(intent);
            finish();

        }


    }
}
