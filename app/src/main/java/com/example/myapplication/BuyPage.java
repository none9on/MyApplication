package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class BuyPage extends AppCompatActivity {
    EditText name, surname;
    TextView title, date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_page);


        Button buyButton = findViewById(R.id.buyButton);
        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BuyPage.this, PaymentPage.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        });
        name = findViewById(R.id.editTextName);
        surname = findViewById(R.id.editTextSurname);
        title = findViewById(R.id.buyTitle);
        date = findViewById(R.id.buyDate);
        date.setText(getIntent().getStringExtra("eventDate"));
        title.setText(getIntent().getStringExtra("eventTitle"));
        ConstraintLayout buyPageBg = findViewById(R.id.buyPageBg);
        buyPageBg.setBackgroundColor(getIntent().getIntExtra("eventBg", 0));


//        ListView orderList = findViewById(R.id.listViewBuy);
//        List<String> eventsTitle = new ArrayList<>();
//        for(Events c: SearchPage.fullEventsList){
//            if(Order.events_id.contains(c.getId())){
//                eventsTitle.add(c.getName());
//            }
//        }
//        orderList.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, eventsTitle));

    }
}