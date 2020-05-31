package com.example.employeetracker.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.employeetracker.GPSTracker;
import com.example.employeetracker.MainActivity;
import com.example.employeetracker.R;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.EmployeeLogin;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.example.employeetracker.util.Utility;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Employee_Login extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText mUserId,mUserPassword;
    Button mbtnLogin;
    ImageView profileimage;
    Intent intent;
    GPSTracker gps;
    Bitmap bitmap;

    double latitude,longitude;
int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplyeelogin);
        intent=new Intent(Employee_Login.this,GPSTracker.class);





        initViews();
    }
    private void initViews()
    {
        mUserId=(TextInputEditText)findViewById(R.id.et_userid);
        mUserPassword=(TextInputEditText)findViewById(R.id.et_password);
        mbtnLogin=(Button)findViewById(R.id.bt_login);
        profileimage=(ImageView)findViewById(R.id.profile);
        profileimage.setOnClickListener(this);
        mbtnLogin.setOnClickListener(this);
    }


    private void captureImage()
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,1000);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(1000 == requestCode){
            bitmap = (Bitmap)data.getExtras().get("data");
            profileimage.setImageBitmap(bitmap);

        }
    }


    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    @Override
    public void onClick(View view) {


        switch (view.getId())
        {
            case R.id.bt_login:
                if (Utility.checkLocationPermission(this))
                {
                    getLocation();
                    startActivity(intent);
                }
                if(validate())
                {
                    adminLogin(getStringImage(bitmap));
                }

                break;
            case R.id.profile:
                if(Utility.checkCameraPermission(this))
                {
                    captureImage();

                }

                break;

        }


    }



    private boolean validate()
    {
        if(bitmap==null)
        {
            Toast.makeText(this,"You need to select Capture Image",Toast.LENGTH_LONG).show();
        }
        else if(latitude==0)
        {
            Toast.makeText(this,"You need to Share Your Location",Toast.LENGTH_LONG).show();
        }
        else if (longitude==0)
        {
            Toast.makeText(this,"You need to Share Your Location",Toast.LENGTH_LONG).show();

        }
        else if(mUserId.getText().toString().isEmpty())
        {
            mUserId.requestFocus();
            mUserPassword.setError("Enter user id");
        }
        else if(mUserPassword.getText().toString().isEmpty())
        {
         mUserPassword.requestFocus();
            mUserPassword.setError("Enter Password");
        }
        else
        {
            return true;
        }
        return false;
    }

    private void getLocation()
    {
        gps = new GPSTracker(Employee_Login.this);

        // check if GPS enabled
        if(gps.canGetLocation()){

            latitude = gps.getLatitude();
            longitude = gps.getLongitude();

            // \n is for new line
            Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
        }else{
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();
        }
    }


    private void adminLogin(String img)
    {

        ProgressUtil.showDialog(this,"Try Login","Please Wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<EmployeeLogin>call= apiService.empLogin(mUserId.getText().toString(),mUserPassword.getText().toString(),String.valueOf(latitude),String.valueOf(longitude),img);
        call.enqueue(new Callback<EmployeeLogin>() {
            @Override
            public void onResponse(Call<EmployeeLogin> call, Response<EmployeeLogin> response) {
                if(response!=null)
                {
                    if(response.body().getMsg().equals("success"))
                    {

                        Log.d("I am in Sucess","Success");

                        PrefUtil.putString(Employee_Login.this,ApiParams.UID,ApiParams.UID,response.body().getUid());
                        startActivity(new Intent(Employee_Login.this,EmpolyeeTaskList.class));
                       /* startActivity(new Intent(Employee_Login.this, AdminDashBoard.class));

                        PrefUtil.putString(Employee_Login.this,ApiParams.UID,ApiParams.UID,response.body().getUid());
                        finish();
                   */ }
                    else
                    {
                        Toast.makeText(getApplicationContext(),response.body().getMsg(),Toast.LENGTH_LONG).show();
                    }
                    ProgressUtil.hideDialog();

                }
                else
                {
                    Toast.makeText(Employee_Login.this,"Sorry! Something is went Wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmployeeLogin> call, Throwable t) {

                Log.d("Failure",t.getMessage());
                Toast.makeText(Employee_Login.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
                ProgressUtil.hideDialog();

            }
        });



    }



}
