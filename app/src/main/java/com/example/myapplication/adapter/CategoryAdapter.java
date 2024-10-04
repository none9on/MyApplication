package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.SearchPage;
import com.example.myapplication.model.Category;

import java.util.List;
//кароч кратко с помрощью этого класса будет показываться что будем передавать в серче
//всё будет наследовать от объекста ресайклер вью
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
//    для использования дизайна для категории с помощью категори вью холдер
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View categoryItems = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
//        что будем конкретно в каждое поле подставлять
        holder.categoryTitle.setText(categories.get(position).getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchPage.showEventsByCategory(categories.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
//файнал класс - не может иметь наследников
    public static final class CategoryViewHolder extends RecyclerView.ViewHolder{

        TextView categoryTitle;
//с какими полями будем взаимодействовать
    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryTitle = itemView.findViewById(R.id.categoryTitle);
    }
}
}
