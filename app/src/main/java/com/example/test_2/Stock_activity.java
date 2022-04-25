package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.test_2.Clients.ApiClient_Stock;
import com.example.test_2.Clients.ApiInterface;
import com.example.test_2.Stock_market.RecyclerAdapter_Stock_Activity;
import com.example.test_2.Stock_market.Stock_Activity_Init;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Stock_activity extends AppCompatActivity {
    List<Stock_Activity_Init> movieList;
    RecyclerView recyclerView;
    RecyclerAdapter_Stock_Activity recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_stock);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter_Stock_Activity(getApplicationContext(),movieList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService = ApiClient_Stock.getClient().create(ApiInterface.class);
        Call<List<Stock_Activity_Init>> call = apiService.getStock_Activity("usd", "market_cap_desc", 100, 1, false);

        call.enqueue(new Callback<List<Stock_Activity_Init>>() {
            @Override
            public void onResponse(Call<List<Stock_Activity_Init>> call, Response<List<Stock_Activity_Init>> response) {
                movieList = response.body();
                Log.d("TAG","Response = "+movieList);
                recyclerAdapter.setMovieList(movieList);
            }

            @Override
            public void onFailure(Call<List<Stock_Activity_Init>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}