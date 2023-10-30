package com.example.mvvmproject.network;

import com.example.mvvmproject.model.TodoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/todos")
    Call<List<TodoResponse>> getAllTodos();
    @GET("/todos/1")
    Call<List<TodoResponse>> getAllTodos();
}
