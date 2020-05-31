package com.example.employeetracker.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.employeetracker.R;
import com.example.employeetracker.adapter.LoginAdapter;
import com.example.employeetracker.api.ApiClient;
import com.example.employeetracker.api.ApiParams;
import com.example.employeetracker.api.ApiService;
import com.example.employeetracker.listeners.ItemUpdateAndDeleteListener;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.EmpTrackResponse;
import com.example.employeetracker.model.EmployeApiResponse;
import com.example.employeetracker.model.Employee;
import com.example.employeetracker.model.EmployeeTaskList;
import com.example.employeetracker.model.Login;
import com.example.employeetracker.model.LoginResponse;
import com.example.employeetracker.model.Tracking;
import com.example.employeetracker.util.PrefUtil;
import com.example.employeetracker.util.ProgressUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by aayu on 2/6/2017.
 */

public class EmployeeLogs extends AppCompatActivity implements ItemUpdateAndDeleteListener,OnMapReadyCallback {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LoginAdapter loginAdapter;
    List<Login> loginList;
    List<String> employeNames;
    List<Employee>employeeList;
    LoginResponse loginResponse;
    Spinner employee;
    Employee mEmployee;
    List<Tracking>trackings;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emplogins);
        loginResponse= new Gson().fromJson(PrefUtil.getString(EmployeeLogs.this,
                ApiParams.UPDATE_ADMIN,ApiParams.UPDATE_ADMIN),LoginResponse.class);

        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_employeelist);
        recyclerView.setLayoutManager(layoutManager);
        trackings=new ArrayList<>();
        loginList=new ArrayList<>();
        employee=(Spinner)findViewById(R.id.spin_employess);
        employee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                    mEmployee=employeeList.get(i);
                    getEmployeeLogs(mEmployee);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        getEmployeList();
         mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

    }





    private  void getEmployeList()
    {

        employeNames=new ArrayList<>();
        employeeList=new ArrayList<>();
        ProgressUtil.showDialog(EmployeeLogs.this,"Loading Data","Please Wait...");
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
                        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(EmployeeLogs.this,android.R.layout.simple_spinner_dropdown_item,employeNames);
                        employee.setAdapter(arrayAdapter);
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
                Toast.makeText(EmployeeLogs.this,"Sorry! Problem on Server",Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getEmployeeLogs(Employee employee)
    {
        ProgressUtil.showDialog(EmployeeLogs.this,"Loading Employee Record","Please wait...");
        ApiService apiService= ApiClient.getApiClient();
        Call<EmpTrackResponse>call=apiService.getEmpolyeeTracks(employee.getId());
        call.enqueue(new Callback<EmpTrackResponse>() {
            @Override
            public void onResponse(Call<EmpTrackResponse> call, Response<EmpTrackResponse> response) {

                ProgressUtil.hideDialog();
                if(response.body()!=null)
                {
                    if (response.body().getMsg().equals("success"))
                    {
                        loginList=response.body().getLogins();
                        loginAdapter=new LoginAdapter(EmployeeLogs.this,loginList,response.body().getImagePath());
                        trackings= response.body().getTrackings();
                        recyclerView.setAdapter(loginAdapter);
                        mapFragment.getMapAsync(EmployeeLogs.this);
                    }

                }

            }

            @Override
            public void onFailure(Call<EmpTrackResponse> call, Throwable t) {

                ProgressUtil.hideDialog();
                Toast.makeText(EmployeeLogs.this,"Sorry! Something is went wrong",Toast.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void deleteItem(int position) {

    }

    @Override
    public void updateItem(int position) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(trackings.size()!=0)
        {
            for(int i=0;i<trackings.size();i++)
            {
                Tracking tracking=trackings.get(i);
                Double lat=Double.parseDouble(tracking.getLatitude());
                Double lng=Double.parseDouble(tracking.getLongitude());
                LatLng latLng=new LatLng(lat,lng);
                mMap.addMarker(new MarkerOptions().position(latLng).title(tracking.getLocation()));
            }
        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(20.593684,78.962880);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,5));


    }
}
