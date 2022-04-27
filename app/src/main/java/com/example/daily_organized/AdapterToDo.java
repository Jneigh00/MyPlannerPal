package com.example.daily_organized;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.daily_organized.Database.Event;
import com.example.daily_organized.Database.EventDatabase;

import java.util.List;

public class AdapterToDo extends RecyclerView.Adapter<AdapterToDo.MyViewHolder> {

    Context context;
    List<Event> eventList;

    MediaPlayer player;


    public AdapterToDo(Context ct){
        this.context = ct;
    }

    public void setEventList(List<Event> eventList) {
       /* if(!this.eventList.equals(eventList)) {
            this.eventList = eventList;
            notifyDataSetChanged();
        }else */

        this.eventList = eventList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdapterToDo.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterToDo.MyViewHolder holder, int position) {
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
        ImageView doneImg;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameD = itemView.findViewById(R.id.eventNameD);
            eventDescD = itemView.findViewById(R.id.eventDescD);
            doneImg = itemView.findViewById(R.id.checkDoneBtn);

            doneImg.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(view.equals(doneImg))
                checkDone(getAdapterPosition());
        }

        private void checkDone(int position) {
            if(player == null){
                player = MediaPlayer.create(doneImg.getContext(), R.raw.sound);
            }
            player.start();
            int id = eventList.get(position).id;
            EventDatabase db = EventDatabase.getDatabase(doneImg.getContext());
            db.eventDAO().updateDone(id);
            eventList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, eventList.size());
        }


    }
}
