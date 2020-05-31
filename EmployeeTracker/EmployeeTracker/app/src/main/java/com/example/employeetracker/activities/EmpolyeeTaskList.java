package com.example.employeetracker.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.employeetracker.GPSTracker;
import com.example.employeetracker.MainActivity;
import com.example.employeetracker.R;
import com.example.employeetracker.adapter.EmpTaskAdapter;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.listeners.SubmitTaskListener;
import com.example.employeetracker.model.AdminTaskList;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.EmpTaskListResponse;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.EmployeeTaskList;
import com.example.employeetracker.model.LoginResponse;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.example.employeetracker.util.Utility;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayu on 1/2/2017.
 */
public class EmpolyeeTaskList extends AppCompatActivity implements SubmitTaskListener {

    LocationManager locationManager;
    double longitudeBest, latitudeBest;
    double longitudeGPS, latitudeGPS;
    double longitudeNetwork, latitudeNetwork;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    EmpTaskAdapter taskAdapter;
    List<EmployeeTaskList> employeeList;
    LoginResponse loginResponse;
    Button logout;
    GPSTracker gps;
    String uid;
    double latitude,longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        setContentView(R.layout.activity_emplist);
        employeeList = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_employeelist);
        logout=(Button)findViewById(R.id.bt_logout);
        logout.setVisibility(View.VISIBLE);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmpolyeeTaskList.this,FirstScreen.class));
                finish();
            }
        });
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        Gson gson = new Gson();
        Log.d("AdminDetail", PrefUtil.getString(EmpolyeeTaskList.this,
                ApiParams.UPDATE_ADMIN, ApiParams.UPDATE_ADMIN));
        loginResponse = gson.fromJson(PrefUtil.getString(EmpolyeeTaskList.this,
                ApiParams.UPDATE_ADMIN, ApiParams.UPDATE_ADMIN), LoginResponse.class);
        uid = PrefUtil.getString(EmpolyeeTaskList.this, ApiParams.UID, ApiParams.UID);
        getTaskeList(uid);
    }

    private void getTaskeList(String uid) {
        ApiService apiService = ApiClient.getApiClient();
        Call<EmpTaskListResponse> call = apiService.empTaskListm(uid);
        call.enqueue(new Callback<EmpTaskListResponse>() {


            @Override
            public void onResponse(Call<EmpTaskListResponse> call, Response<EmpTaskListResponse> response) {
                if (response.body().getMsg().equals("success")) {
                    employeeList = response.body().getTaskList();

                    taskAdapter = new EmpTaskAdapter(EmpolyeeTaskList.this, employeeList);

                    recyclerView.setAdapter(taskAdapter);
                } else {
                    Toast.makeText(getApplicationContext(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<EmpTaskListResponse> call, Throwable t) {

                Log.d("Failure", t.getMessage());
                Toast.makeText(EmpolyeeTaskList.this, "Sorry! Problem on Server", Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void submitTask(int id) {
        submitTask(employeeList.get(id));


    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utility.checkLocationPermission(this))
        {
            if(checkLocation())
            {
               locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER, 60 * 500, 10, locationListenerNetwork);
            }
        }
        uploadTracks(uid,String.valueOf(latitude),String.valueOf(longitude));
    }


    private boolean checkLocation() {
        if(!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }
    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeNetwork = location.getLongitude();
            latitudeNetwork = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    uploadTracks(uid,String.valueOf(latitudeNetwork),String.valueOf(longitudeNetwork));




                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
    private void showAlert() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }
    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    private  void uploadTracks(String employee, String latitude, String longtitude)
    {
        ApiService apiService=ApiClient.getApiClient();
        Call<ApiResponse>call=apiService.trackEmpolyee(employee,latitude,longtitude);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                Log.d("Response",response.body().getMsg());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.d("Failure",t.getMessage());

            }
        });
    }


    private void submitTask(final EmployeeTaskList employeeTaskList) {

        ProgressUtil.showDialog(EmpolyeeTaskList.this, "Submitting Task", "Please Wait");
        ApiService apiClient = ApiClient.getApiClient();
        Call<ApiResponse> call = apiClient.submitTask(employeeTaskList.getId(), PrefUtil.getString(EmpolyeeTaskList.this, ApiParams.UID, ApiParams.UID));
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                ProgressUtil.hideDialog();
                if (response.body()!=null)
                {
                    if(response.body().getMsg().equals("success"))
                    {
                        EmployeeTaskList tempemployeeTask=employeeTaskList;
                        employeeList.remove(tempemployeeTask);
                        employeeTaskList.setStatus("1");
                        employeeList.add(employeeTaskList);
                        taskAdapter.notifyDataSetChanged();
                    }

                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                ProgressUtil.hideDialog();

                Log.d("Failure",t.getMessage());

            }
        });
    }


}

