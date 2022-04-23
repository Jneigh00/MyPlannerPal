package com.example.daily_organized;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_organized.Database.Event;

import java.util.List;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.MyViewHolder> {

    Context context;
    List<Event> eventList;

    public AdapterToDo(Context ct){
        this.context = ct;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterToDo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull AdapterToDo.MyViewHolder holder, int position) {
        holder.eventNameD.setText(this.eventList.get(position).name);
        holder.eventDescD.setText(this.eventList.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView eventNameD;
        TextView eventDescD;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameD = itemView.findViewById(R.id.eventNameD);
            eventDescD = itemView.findViewById(R.id.eventDescD);
        }
    }
}
