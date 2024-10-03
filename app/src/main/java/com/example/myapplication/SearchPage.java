package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.CategoryAdapter;
import com.example.myapplication.adapter.EventsAdapter;
import com.example.myapplication.model.Category;
import com.example.myapplication.model.Events;

import java.util.ArrayList;
import java.util.List;

public class SearchPage extends AppCompatActivity {
    private Button movefive;
    private Button movetwosix;
    EventsAdapter eventsAdapter;

    RecyclerView categoryRecycler, eventsRecycler;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        ImageButton movefive = (ImageButton) findViewById(R.id.profile_page_notbold);
        movefive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, ProfilePage.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        });
        ImageButton movesix = (ImageButton) findViewById(R.id.home_page_notboldsearch);
        movesix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchPage.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
            }
        });

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(new Category(1, "Кино"));
        categoryList.add(new Category(2, "Музеи"));
        categoryList.add(new Category(3, "Театры"));
        categoryList.add(new Category(4, "Концерты"));
        categoryList.add(new Category(5, "Лекции"));
        categoryList.add(new Category(6, "Развлечения"));
        categoryList.add(new Category(7, "Прочее"));

        setCategoryRecycler(categoryList);

        List<Events> eventsList = new ArrayList<>();
        eventsList.add(new Events(1, "30 ноября", "test", "TEST", "#fcba03"));
        eventsList.add(new Events(2, "5 декабря", "testtwo", "TEST TWO", "#f194f2"));

        setEventRecycler(eventsList);

    }

    private void setEventRecycler(List<Events> eventsList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        находим его по айди
        eventsRecycler = findViewById(R.id.eventsRecycler);
//        передаем настройки которые прописали чуть чуть выше
        eventsRecycler.setLayoutManager(layoutManager);
//        для вывода всех значений
        eventsAdapter = new EventsAdapter(this, eventsList);
        eventsRecycler.setAdapter(eventsAdapter);
    }

    private void setCategoryRecycler(List<Category> categoryList) {
//        указываем как будет выглядеть этот ресайклер
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
//        находим его по айди
        categoryRecycler = findViewById(R.id.categoryRecycler);
//        передаем настройки которые прописали чуть чуть выше
        categoryRecycler.setLayoutManager(layoutManager);
//        для вывода всех значений
        categoryAdapter = new CategoryAdapter(this, categoryList);
        categoryRecycler.setAdapter(categoryAdapter);

    }
}