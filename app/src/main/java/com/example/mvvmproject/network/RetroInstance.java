package com.example.mvvmproject.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroInstance {
private static final String URL="https://jsonplaceholder.typicode.com/";

private static RetroInstance retroInstance;
private Retrofit retrofit;

private RetroInstance(){
    retrofit=new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
public static synchronized RetroInstance getInstance(){
    if(retroInstance ==null){
        retroInstance=new RetroInstance();
    }
    return retroInstance;
}
public API getAPI(){
    return retrofit.create(API.class);
}

}
