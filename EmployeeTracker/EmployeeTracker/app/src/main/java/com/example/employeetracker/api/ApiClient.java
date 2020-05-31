package com.example.employeetracker.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aayu on 12/31/2016.
 */
public class ApiClient {

    static ApiService apiService;

    public static ApiService getApiClient()
    {
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiParams.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService apiService=retrofit.create(ApiService.class);
        return apiService;
    }
}
