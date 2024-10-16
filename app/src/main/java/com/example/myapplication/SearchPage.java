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

    RecyclerView categoryRecycler, eventsRecycler;
    CategoryAdapter categoryAdapter;
    static EventsAdapter eventsAdapter;
    static List<Events> eventsList = new ArrayList<>();
    static List<Events> fullEventsList = new ArrayList<>();


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


        eventsList.add(new Events(1, "30 ноября", "birdlady", "Леди Бёрд", "#c7656d", "testestest", "paricipant n 1", 1));
        eventsList.add(new Events(2, "5 декабря", "prideand", "Гордость и предубеждение", "#4C727F", "test2test2test2", "participant n 2", 1));
        eventsList.add(new Events(3, "10 декабря", "smena", "Смена: выставка такая-то", "#A9835F", "test2test2test2", "participant n 2", 2));
        eventsList.add(new Events(4, "5 января", "gsi", "ГСИ РТ", "#D5DCD5", "test2test2test2", "participant n 2", 2));
        eventsList.add(new Events(5, "8 января", "ugol", "Угол", "#9B9A9A", "test2test2test2", "participant n 2", 3));
        eventsList.add(new Events(6, "20 января", "opera", "Театр оперы и балета", "#74251D", "test2test2test2", "participant n 2", 3));
        eventsList.add(new Events(7, "6 января", "hanz", "Hanz Zimmer", "#D50551", "test2test2test2", "participant n 2", 4));
        eventsList.add(new Events(8, "12 января", "svechi", "Саунтрэки Эйнауди", "#DC6202", "test2test2test2", "participant n 2", 4));
        eventsList.add(new Events(9, "29 января", "smenalec", "Смена: книги меняют города", "#C39E8B", "test2test2test2", "participant n 2", 5));
        eventsList.add(new Events(10, "2 февраля", "bibl", "Национальная библиотека лекция", "#AB746C", "test2test2test2", "participant n 2", 5));
        eventsList.add(new Events(11, "10 февраля", "surf", "Surf Coffee капинг", "#76B9B3", "test2test2test2", "participant n 2", 6));
        eventsList.add(new Events(12, "15 февраля", "flower", "Флористика", "#905E74", "test2test2test2", "participant n 2", 6));
        eventsList.add(new Events(10, "17 февраля", "club", "Книжный клуб", "#C59980", "test2test2test2", "participant n 2", 7));
        eventsList.add(new Events(13, "22 февраля", "speaking", "English Speaking club", "#95AF70", "test2test2test2", "participant n 2", 7));


        fullEventsList.addAll(eventsList);
        setEventRecycler(eventsList);

        ImageButton allEvents = (ImageButton) findViewById(R.id.imageViewButton);
        allEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventsList.clear();
                eventsList.addAll(fullEventsList);
                eventsAdapter.notifyDataSetChanged();

            }
        });

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

    public static void showEventsByCategory(int category){

        eventsList.clear();
        eventsList.addAll(fullEventsList);
        List<Events> filterEvents = new ArrayList<>();
        for(Events e: eventsList){
            if(e.getCategory() == category){
                filterEvents.add(e);}
        }
        eventsList.clear();
        eventsList.addAll(filterEvents);
        eventsAdapter.notifyDataSetChanged();
    }
}