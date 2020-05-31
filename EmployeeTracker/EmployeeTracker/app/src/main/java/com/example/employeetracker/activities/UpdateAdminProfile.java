package com.example.employeetracker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.LoginResponse;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.google.gson.Gson;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayu on 1/2/2017.
 */
public class UpdateAdminProfile extends AppCompatActivity {


    TextInputEditText mName,mEmail;
    Button mupdatAdmin;
    LoginResponse loginResponse;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateadmin);
        mName=(TextInputEditText)findViewById(R.id.et_adminname);
        loginResponse= new Gson().fromJson(PrefUtil.getString(UpdateAdminProfile.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN),LoginResponse.class);

        mupdatAdmin=(Button)findViewById(R.id.bt_adminprofile);
        mEmail=(TextInputEditText)findViewById(R.id.et_adminemail);
        if(loginResponse!=null)
        {
            mName.setText(loginResponse.getName());
            mEmail.setText(loginResponse.getEmail());
        }
        mupdatAdmin.setOnClickListener(new View.OnClickListener() {
            int i=0;
            @Override
            public void onClick(View view) {
                i++;
                if(i==1)
                {
                    mEmail.setEnabled(true);
                    mName.setEnabled(true);
                }
                if(i>2)
                {
                    if(validate())
                    {
                        updateAdminProfile();
                    }

                }


            }
        });

            }

    private boolean validate()
    {
        if(mName.getText().toString().isEmpty())
        {
            mName.requestFocus();
            mName.setError("You Can't Left this Field Blank");

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(mEmail.getText().toString()).matches())
        {
            mEmail.getText().toString();
            mEmail.setError("Please Enter Valid Email Address");

        }
        else
        {
            return  true;
        }
        return false;
    }


    private  void updateAdminProfile()
    {
        ProgressUtil.showDialog(UpdateAdminProfile.this,"Updateing Profile","Please Wait.....");
        ApiService apiService= ApiClient.getApiClient();
        Call<ApiResponse> call=apiService.updateAdminProfile(loginResponse.getUid(),mName.getText().toString(),mEmail.getText().toString());
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
            ProgressUtil.hideDialog();
                if(response.body().getMsg().equals("success"))
                {
                    Toast.makeText(getApplicationContext(),"Your Profile Successfully updated",Toast.LENGTH_LONG).show();
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
                Toast.makeText(UpdateAdminProfile.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();

            }
        });
    }
}
