package com.example.myapplication.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.EventPage;
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
        holder.eventBg.setCardBackgroundColor(Color.parseColor(events.get(holder.getAdapterPosition()).getColor()));
//        преобразовываем картинку у нужному типу данных
        int imageId = context.getResources().getIdentifier(events.get(holder.getAdapterPosition()).getImg(), "drawable", context.getPackageName());
        holder.eventImg.setImageResource(imageId);
        holder.eventTitle.setText(events.get(holder.getAdapterPosition()).getName());
        holder.eventDate.setText(events.get(holder.getAdapterPosition()).getDate());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventPage.class);

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                        (Activity) context,
                        new Pair<View, String>(holder.eventImg, "eventImage"));

                intent.putExtra("eventBg",Color.parseColor(events.get(holder.getAdapterPosition() ).getColor()));
                intent.putExtra("eventImg",imageId);
                intent.putExtra("eventTitle",events.get(holder.getAdapterPosition()).getName());
                intent.putExtra("eventDate",events.get(holder.getAdapterPosition()).getDate());
                intent.putExtra("eventDesc",events.get(holder.getAdapterPosition()).getDesc());
                intent.putExtra("eventPart",events.get(holder.getAdapterPosition()).getPart());


                context.startActivity(intent, options.toBundle());
            }
        });

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
