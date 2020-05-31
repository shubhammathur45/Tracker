package com.example.employeetracker;

import android.content.Intent;
import android.preference.Preference;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeetracker.activities.AdminDashBoard;
import com.example.employeetracker.activities.ResetPassword;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.LoginResponse;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.google.gson.Gson;
import com.google.gson.annotations.JsonAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText mUserId,mUserPassword;
    Button mbtnLogin;
    TextView forgotpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }
    private void initViews()
    {
        mUserId=(TextInputEditText)findViewById(R.id.et_userid);
        mUserPassword=(TextInputEditText)findViewById(R.id.et_password);
        mbtnLogin=(Button)findViewById(R.id.bt_login);
        forgotpassword=(TextView)findViewById(R.id.tv_forgotpass);
        forgotpassword.setOnClickListener(this);
        mbtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.bt_login:
                adminLogin();
                break;
            case R.id.tv_forgotpass:
                startActivity(new Intent(MainActivity.this, ResetPassword.class));

                break;
        }


    }

    private void adminLogin()
    {
        ProgressUtil.showDialog(this,"Try Login","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<LoginResponse>call= apiService.adminLogin(mUserId.getText().toString(),mUserPassword.getText().toString());
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response!=null)
                {
                    if(response.body().getMsg().equals("success"))
                    {
                        startActivity(new Intent(MainActivity.this, AdminDashBoard.class));

                        PrefUtil.putString(MainActivity.this,ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN,new Gson().toJson(response.body()));
                        finish();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    }
                    ProgressUtil.hideDialog();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Sorry! Something is went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(MainActivity.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });



    }

}
