package com.example.employeetracker.api;

import com.example.employeetracker.model.AdminTaskList;
import com.example.employeetracker.model.ApiResponse;
import com.example.employeetracker.model.EmpTaskListResponse;
import com.example.employeetracker.model.EmpTrackResponse;
import com.example.employeetracker.model.EmployeApiResponse;
import com.example.employeetracker.model.EmployeeLogin;
import com.example.employeetracker.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aayu on 12/31/2016.
 */
public interface ApiService {

    @FormUrlEncoded
    @POST(ApiParams.LOGIN_URL)
    Call<LoginResponse>adminLogin(@Field(ApiParams.KEY_EMAIL)String email, @Field(ApiParams.KEY_PASSWORD)String password);
    @FormUrlEncoded
    @POST(ApiParams.RESET_PASSWORD)
    Call<ApiResponse>resetPassword(@Field(ApiParams.KEY_TOKEN)String token,@Field(ApiParams.KEY_PASSWORD)String password);
    @FormUrlEncoded
    @POST(ApiParams.UPDATE_ADMIN)
    Call<ApiResponse>updateAdminProfile(@Field(ApiParams.UID)String uid,@Field(ApiParams.NAME)String name,@Field(ApiParams.KEY_EMAIL)String email, @Field(ApiParams.KEY_PASSWORD)String password);
    @FormUrlEncoded
    @POST(ApiParams.EMPLOYEE_REGISTRATION)
    Call<ApiResponse>addEmpolyee(@Field(ApiParams.NAME)String name,@Field(ApiParams.KEY_EMAIL)String email,
                                 @Field(ApiParams.KEY_PASSWORD)String password,@Field(ApiParams.PHONE)String phone,
                                 @Field(ApiParams.KEY_EMP_ID)String emp_id);
    @FormUrlEncoded
    @POST(ApiParams.EMPLOYEE_LIST)
    Call<EmployeApiResponse>getAllEmployee(@Field(ApiParams.UID)String uid);


    @FormUrlEncoded
    @POST(ApiParams.ALL_TASKS)
    Call<AdminTaskList>getAllTasks(@Field(ApiParams.UID)String uid);
    @FormUrlEncoded
    @POST(ApiParams.UPDATE_EMPLOYEE_PROFILE)
    Call<ApiResponse>updateEmployeeProfile(@Field(ApiParams.NAME)String name,@Field(ApiParams.KEY_EMAIL)String email,
                                         @Field(ApiParams.PHONE)String phone,
                                         @Field(ApiParams.UID)String emp_id);
    @FormUrlEncoded
    @POST(ApiParams.CREATE_TASK)
    Call<ApiResponse>createTask(@Field(ApiParams.EMP_ID)String uid,@Field(ApiParams.TASK)String name,@Field(ApiParams.KEY_LOCATION)String location,
                                         @Field(ApiParams.LAST_DATE)String lastdate);

    @FormUrlEncoded
    @POST("updateprofile.php")
    Call<ApiResponse>updateAdminProfile(@Field(ApiParams.UID)String uid,@Field(ApiParams.NAME)String name,@Field(ApiParams.KEY_EMAIL)String email);
    @FormUrlEncoded
    @POST("deleteemployee.php")
    Call<ApiResponse>deleteEmployee(@Field(ApiParams.KEY_EMP_ID)String emid);
    @FormUrlEncoded
    @POST("deletetask.php")
    Call<ApiResponse>deleteTask(@Field(ApiParams.TASK_ID)String taskid);
    @FormUrlEncoded
    @POST("forgotpasswordapi.php")
    Call<ApiResponse>forgotPassword(@Field(ApiParams.KEY_EMAIL)String email);

    @FormUrlEncoded
    @POST(ApiParams.EMPOLYEE_TRACKING)
    Call<ApiResponse>trackEmpolyee(@Field(ApiParams.KEY_EMP_ID)String empid,@Field(ApiParams.KEY_LAT)String lat,@Field(ApiParams.KEY_LNG)String lng);
    @FormUrlEncoded
    @POST("employee-log.php")
    Call<ApiResponse>getLogs(@Field(ApiParams.KEY_EMP_ID)String emp_id);
    @FormUrlEncoded
    @POST("employeelogin.php")
    Call<EmployeeLogin>empLogin(@Field(ApiParams.KEY_EMAIL)String email,@Field(ApiParams.KEY_PASSWORD)String password,
                                @Field(ApiParams.KEY_LAT)String lat,@Field(ApiParams.KEY_LNG)String lng,@Field(ApiParams.KEY_IMAGE)String image);

    @FormUrlEncoded
    @POST("track-employee-location.php")
    Call<ApiResponse>empTracker(@Field(ApiParams.KEY_EMP_ID)String empid,@Field(ApiParams.KEY_LAT)String lat,@Field(ApiParams.KEY_LNG)String lng)
            ;
    @FormUrlEncoded
    @POST("listallemptask.php")
    Call<EmpTaskListResponse>empTaskListm(@Field(ApiParams.UID)String uid);
    @FormUrlEncoded
    @POST("submit-task.php")
    Call<ApiResponse>submitTask(@Field(ApiParams.TASK_ID)String task_id,@Field(ApiParams.EMP_ID)String empid);
    @FormUrlEncoded
    @POST("employee-log.php")
    Call<EmpTrackResponse>getEmpolyeeTracks(@Field(ApiParams.KEY_EMP_ID)String empid);
    @FormUrlEncoded
    @POST("employee-log.php")
    Call<ApiResponse>EmpolyeeTracks(@Field(ApiParams.KEY_EMP_ID)String empid,@Field(ApiParams.KEY_LAT)String lat,@Field(ApiParams.KEY_LNG)String lng);
    @FormUrlEncoded
    @POST("updateemployeetask.php")
    Call<ApiResponse>updateTask(@Field(ApiParams.TASK_ID)String taskid,@Field(ApiParams.EMP_ID)String uid,@Field(ApiParams.TASK)String name,@Field(ApiParams.KEY_LOCATION)String location,
                                @Field(ApiParams.LAST_DATE)String lastdate);


















}
