package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EventPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        Button movebuy = findViewById(R.id.ticketButton);
        movebuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventPage.this, BuyPage.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        });
        ImageButton moveback =(ImageButton) findViewById(R.id.backButton);
        moveback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventPage.this, SearchPage.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        });
        ConstraintLayout eventBg = findViewById(R.id.eventPageBg);
        ImageView eventImage = findViewById(R.id.eventPageImage);
        TextView eventTitle = findViewById(R.id.EventPageName);
        TextView eventDate = findViewById(R.id.EventPageDate);
        TextView eventDesc = findViewById(R.id.eventPageDesc);
        TextView eventPart = findViewById(R.id.eventPagePart);

        eventBg.setBackgroundColor(getIntent().getIntExtra("eventBg", 0));
        eventImage.setImageResource(getIntent().getIntExtra("eventImg", 0));
        eventTitle.setText(getIntent().getStringExtra("eventTitle"));
        eventDate.setText(getIntent().getStringExtra("eventDate"));
        eventDesc.setText(getIntent().getStringExtra("eventDesc"));
        eventPart.setText(getIntent().getStringExtra("eventPart"));




    }
}