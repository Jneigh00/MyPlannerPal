package com.example.daily_organized;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_organized.Database.Event;
import com.example.daily_organized.Database.EventDatabase;

import java.util.List;

public class AdapterDone extends RecyclerView.Adapter<AdapterDone.MyViewHolder>{

    Context context;
    List<Event> eventList;

    public AdapterDone(Context ct){
        this.context = ct;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterDone.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row_done, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDone.MyViewHolder holder, int position) {
        holder.eventNameD.setText(this.eventList.get(position).name);
        holder.eventDescD.setText(this.eventList.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return this.eventList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView eventNameD;
        TextView eventDescD;
        ImageView deleteImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameD = itemView.findViewById(R.id.eventNameD);
            eventDescD = itemView.findViewById(R.id.eventDescD);
            deleteImg = itemView.findViewById(R.id.deleteBtn);

            deleteImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.equals(deleteImg))
                checkDone(getAdapterPosition());
        }

        private void checkDone(int position) {
            int id = eventList.get(position).id;
            EventDatabase db = EventDatabase.getDatabase(deleteImg.getContext());
            db.eventDAO().delete(id);
            eventList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, eventList.size());
        }


    }
}
