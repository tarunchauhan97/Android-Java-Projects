package com.example.androidjavaprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface {

    private final String myAPIKey = "f9a1445c523b4aa88f383f6dc0fc408e";
    private final String newsBaseURL = "https://newsapi.org/";
    private RecyclerView newsRV, categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV = findViewById(R.id.idRVCategories);
        loadingPB = findViewById(R.id.idPBLoading);
        articlesArrayList = new ArrayList<>();
        categoryRVModalArrayList = new ArrayList<>();
        newsRVAdapter = new NewsRVAdapter(articlesArrayList, this);
        categoryRVAdapter = new CategoryRVAdapter(categoryRVModalArrayList, this, this::onCategoryClick);
        /////?????//////
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);
        getCategories();

    }

    private void getCategories() {

        String catAll = "https://images.unsplash.com/photo-1570900808791-d530855f79e3?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8YWxsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60";
        String catTechnology = "https://images.unsplash.com/photo-1592725832429-c3154644aec2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String catScience = "https://images.unsplash.com/photo-1570961999607-df226979f156?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8M3x8YWxsfGVufDB8fDB8fA%3D%3D&auto=format&fit=crop&w=500&q=60";
        String catSports = "https://images.unsplash.com/photo-1499714608240-22fc6ad53fb2?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String catGeneral = "https://images.unsplash.com/photo-1600181221306-907bdca60587?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTZ8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String catBusiness = "https://images.unsplash.com/photo-1606639386467-3d28d4e99d64?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTh8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String catEntertainment = "https://plus.unsplash.com/premium_photo-1663853120620-ae7ebc01a36c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTl8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";
        String catHealth = "https://images.unsplash.com/photo-1603855873822-0931a843ee3a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjB8fGFsbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60";

        categoryRVModalArrayList.add(new CategoryRVModal("All", catAll));
        categoryRVModalArrayList.add(new CategoryRVModal("Technology", catTechnology));
        categoryRVModalArrayList.add(new CategoryRVModal("Science", catScience));
        categoryRVModalArrayList.add(new CategoryRVModal("Sports", catSports));
        categoryRVModalArrayList.add(new CategoryRVModal("General", catGeneral));
        categoryRVModalArrayList.add(new CategoryRVModal("Business", catBusiness));
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment", catEntertainment));
        categoryRVModalArrayList.add(new CategoryRVModal("Health", catHealth));
        categoryRVAdapter.notifyDataSetChanged();
    }

    private void getNews(String category) {

        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL = newsBaseURL + "v2/top-headlines?country=in&category=" + category + "&apiKey=" + myAPIKey;
        String allNewsURL = newsBaseURL + "v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=" + myAPIKey;
        Retrofit retrofit = new Retrofit.Builder().baseUrl(newsBaseURL).addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;

    }


    @Override
    public void onCategoryClick(int position) {

    }
}
