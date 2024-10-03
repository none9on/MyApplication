package com.example.myapplication.model;

public class Category {

    int id;
    String title;


    public Category(int id, String title) {
        this.id = id;
        this.title = title;
    }
//получить значение из поля
    public int getId() {
        return id;
    }
//метод для установления определенного значения в поле
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
