package com.example.employeetracker.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeetracker.MainActivity;
import com.example.employeetracker.R;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.LoginResponse;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPassword extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText mUserId,mUserPassword,mToken;
    TextInputLayout mUserIdpnael;

    Button mbtnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass);
        initViews();
    }
    private void initViews()
    {
        mUserId=(TextInputEditText)findViewById(R.id.et_userid);
        mUserPassword=(TextInputEditText)findViewById(R.id.et_password);
        mUserIdpnael=(TextInputLayout)findViewById(R.id.useridpanel);
        mToken=(TextInputEditText)findViewById(R.id.et_token);
        mbtnLogin=(Button)findViewById(R.id.bt_login);
        mbtnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.bt_login:
                if(mbtnLogin.getText().toString().equals("Reset Password"))
                {
                    resetPassword();
                }
                else
                {
                    adminLogin();
                }
                break;
            }


    }

    private void adminLogin()
    {
        ProgressUtil.showDialog(this,"Sending Email","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse>call= apiService.forgotPassword(mUserId.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body().getMsg().equals("success"))
                {
                    mUserId.setVisibility(View.GONE);
                    mUserIdpnael.setVisibility(View.GONE);
                    mToken.setVisibility(View.VISIBLE);
                    mbtnLogin.setText("Reset Password");
                    mUserPassword.setVisibility(View.VISIBLE);
                    Log.d("Response",response.body().getMsg());

                    //startActivity(new Intent(ResetPassword.this, AdminDashBoard.class));

                   // PrefUtil.putString(ResetPassword.this,ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN,new Gson().toJson(response.body()));
                   // finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
                ProgressUtil.hideDialog();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(ResetPassword.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });



    }
    private void resetPassword()
    {
        ProgressUtil.showDialog(this,"Resetting Password","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse>call= apiService.resetPassword(mToken.getText().toString(),mUserPassword.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.body().getMsg().equals("success"))
                {

                    Log.d("Response",response.body().getMsg());
                    Toast.makeText(ResetPassword.this, "Your Password is Successfully Changed", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ResetPassword.this, MainActivity.class));
                    finish();
                    //startActivity(new Intent(ResetPassword.this, AdminDashBoard.class));

                    // PrefUtil.putString(ResetPassword.this,ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN,new Gson().toJson(response.body()));
                    // finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                }
                ProgressUtil.hideDialog();
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(ResetPassword.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });



    }
}
