package com.example.mvvmproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.mvvmproject.adapter.TodoAdapter;
import com.example.mvvmproject.model.TodoResponse;
import com.example.mvvmproject.network.RetroInstance;
import com.example.mvvmproject.viewmodel.MovieListViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements TodoAdapter.ItemClickListener{

    MovieListViewModel movieListViewModel;
    List<TodoResponse> todoResponseList;
    private TodoAdapter todoAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView=findViewById(R.id.recyclerView);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false);
recyclerView.setLayoutManager(linearLayoutManager);
        todoAdapter=new TodoAdapter(this,todoResponseList,this);
        recyclerView.setAdapter(todoAdapter);
        movieListViewModel=new ViewModelProvider(MainActivity.this).get(MovieListViewModel.class);
        movieListViewModel.getTodoListObserver().observe(MainActivity.this, new Observer<List<TodoResponse>>() {
        movieListViewModel=new ViewModelProvider(this).get(MovieListViewModel.class);
        recyclerView.setAdapter(todoAdapter);
        movieListViewModel.getTodoListObserver().observe(this, new Observer<List<TodoResponse>>() {
            @Override
            public void onChanged(List<TodoResponse> todoResponses) {
                todoResponseList=todoResponses;
                todoAdapter.setTodoResponseList(todoResponses);
            }
        });
        movieListViewModel.makeAPICall();
    }

    @Override
    public void onItemClick(TodoResponse todoResponse) {
        Log.v("ITEMCLICKED"," "+todoResponse.getTitle());
    }
}
