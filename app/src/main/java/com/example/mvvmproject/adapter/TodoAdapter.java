package com.example.mvvmproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvmproject.R;
import com.example.mvvmproject.model.TodoResponse;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.MyViewHolder> {
    private Context context;
    private List<TodoResponse> todoResponseList;
    ItemClickListener itemClickListener;

    public TodoAdapter(Context context, List<TodoResponse> todoResponseList) {
        this.context = context;
        this.todoResponseList = todoResponseList;
    }

    public TodoAdapter(Context context, List<TodoResponse> todoResponseList, ItemClickListener itemClickListener) {
        this.context = context;
        this.todoResponseList = todoResponseList;
        this.itemClickListener = itemClickListener;
    }

    public void setTodoResponseList(List<TodoResponse> todoResponseList) {
        this.todoResponseList = todoResponseList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        TodoAdapter.MyViewHolder myHolder = new TodoAdapter.MyViewHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.MyViewHolder holder, int position) {
    holder.textViewText.setText(todoResponseList.get(position).getTitle());
    holder.textViewStatus.setText(todoResponseList.get(position).getCompleted().toString());
    holder.textViewStatus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(todoResponseList.get(position));
        }
    });
    }

    @Override
    public int getItemCount() {
        if(todoResponseList!=null){
            return todoResponseList.size();

        }
    return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewText,textViewStatus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewText=itemView.findViewById(R.id.text);
            textViewStatus=itemView.findViewById(R.id.status);
        }
    }

    public interface ItemClickListener{
        public void onItemClick(TodoResponse todoResponse);
    }
}
