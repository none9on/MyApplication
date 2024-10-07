package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.model.Events;
import com.example.myapplication.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderHistoryPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_page);

        ListView orderList = findViewById(R.id.listViewBuy);
        List<String> eventsTitle = new ArrayList<>();
        for(Events c: SearchPage.fullEventsList){
            if(Order.events_id.contains(c.getId())){
                eventsTitle.add(c.getName());
            }
        }
        orderList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventsTitle));
    }
}