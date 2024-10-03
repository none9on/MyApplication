package com.example.myapplication.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Events;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {
    Context context;
    List<Events> events;

    public EventsAdapter(Context context, List<Events> events) {
        this.context = context;
        this.events = events;
    }

    //    design
    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View eventItems = LayoutInflater.from(context).inflate(R.layout.event_item, parent, false);
        return new EventsAdapter.EventsViewHolder(eventItems);

    }
//what specifically will be in design
    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        holder.eventBg.setCardBackgroundColor(Color.parseColor(events.get(position).getColor()));
//        преобразовываем картинку у нужному типу данных
        int imageId = context.getResources().getIdentifier(events.get(position).getImg(), "drawable", context.getPackageName());
        holder.eventImg.setImageResource(imageId);
        holder.eventTitle.setText(events.get(position).getName());
        holder.eventDate.setText(events.get(position).getDate());

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public static final class EventsViewHolder extends RecyclerView.ViewHolder{

        CardView eventBg;
        ImageView eventImg;
        TextView eventTitle, eventDate;

//with what elements will be working in design
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);

            eventBg = itemView.findViewById(R.id.eventBg);
            eventImg = itemView.findViewById(R.id.eventImage);
            eventTitle = itemView.findViewById(R.id.eventName);
            eventDate = itemView.findViewById(R.id.eventDate);
        }
    }
}
