package com.example.wri.Service;

public class APIService{
    //private static final String BASE_URL = "http://192.168.20.38/wri/";
    private static final String BASE_URL = "https://uni2work.000webhostapp.com/WRI/";
    public static DataService getService(){
        return APIRetrofitClient.getClient(BASE_URL).create(DataService.class);
    }
}

