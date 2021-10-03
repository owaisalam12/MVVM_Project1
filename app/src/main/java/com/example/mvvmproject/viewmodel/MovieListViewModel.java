package com.example.mvvmproject.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmproject.model.TodoResponse;
import com.example.mvvmproject.network.RetroInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieListViewModel extends ViewModel {

MutableLiveData<List<TodoResponse>> todoList;

    public MovieListViewModel() {
        todoList=new MutableLiveData<>();
    }

    public MutableLiveData<List<TodoResponse>> getTodoListObserver() {
        return todoList;
    }
    public void makeAPICall(){
        Call<List<TodoResponse>> call= RetroInstance.getInstance().getAPI().getAllTodos();
        call.enqueue(new Callback<List<TodoResponse>>() {
            @Override
            public void onResponse(Call<List<TodoResponse>> call, Response<List<TodoResponse>> response) {
                List<TodoResponse> body =response.body();
                todoList.postValue(response.body());
                for (TodoResponse todoResponse: body){
                    Log.v("tdos3",""+todoResponse.getTitle());

                }
            }

            @Override
            public void onFailure(Call<List<TodoResponse>> call, Throwable t) {
                Log.v("tdos33",""+t.getMessage());
                todoList.postValue(null);

            }
        });
    }
}
