package com.example.employeetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.util.ProgressUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayu on 1/2/2017.
 */
public class AddEmployee extends AppCompatActivity {


    TextInputEditText mName,mEmail,mPhone,mPassword,mEmp_Id;
    Button mAddEmpolyee;
    Employee employee;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addemployee);
        mName=(TextInputEditText)findViewById(R.id.et_empname);
        mEmp_Id=(TextInputEditText)findViewById(R.id.et_empid);
        mEmail=(TextInputEditText)findViewById(R.id.et_email);
        mPhone=(TextInputEditText)findViewById(R.id.et_phone);
        mPassword=(TextInputEditText)findViewById(R.id.et_password);
        mAddEmpolyee=(Button)findViewById(R.id.bt_addemployee);

        if(getIntent().getExtras()!=null)
        {
            int flag=getIntent().getExtras().getInt(ApiParams.KEY_FLAG);
            employee=getIntent().getExtras().getParcelable(ApiParams.KEY_EMP_ID);
            mName.setText(employee.getName());
            mEmp_Id.setText(employee.getEmpId());
            mEmp_Id.setEnabled(false);
            mPassword.setVisibility(View.GONE);

            mEmail.setText(employee.getEmail());
            mPhone.setText(employee.getPhone());
            mAddEmpolyee.setText("Update Employee");
        }
        mAddEmpolyee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mAddEmpolyee.getText().toString().equals("Update Employee"))
                {
                    upDateEmployee();
                    startActivity(new Intent(AddEmployee.this,EmployeeList.class));
                }
                else
                {
                    addEmployee();
                }

            }
        });
    }


    private  void upDateEmployee()
    {
        ProgressUtil.showDialog(AddEmployee.this,"Updating Employee","Please Wait.....");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse> call=apiService.updateEmployeeProfile(mName.getText().toString(),mEmail.getText().toString(),mPhone.getText().toString(),employee.getId());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ProgressUtil.hideDialog();
                if(response.body().getMsg().equals("success"))
                {
                    Toast.makeText(getApplicationContext(),"Employee Sucessfully Updated",Toast.LENGTH_LONG).show();
                    finish();
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
                Toast.makeText(AddEmployee.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();

            }
        });
    }

    private  void addEmployee()
    {
        ProgressUtil.showDialog(AddEmployee.this,"Adding Employee","Please Wait.....");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse> call=apiService.addEmpolyee(mName.getText().toString(),mEmail.getText().toString(),mPassword.getText().toString(),mPhone.getText().toString(),mEmp_Id.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ProgressUtil.hideDialog();
                if(response.body().getMsg().equals("success"))
                {
                    Toast.makeText(getApplicationContext(),"Employee SucessfullyCreated",Toast.LENGTH_LONG).show();
                    finish();
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
                Toast.makeText(AddEmployee.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();

            }
        });
    }
}
