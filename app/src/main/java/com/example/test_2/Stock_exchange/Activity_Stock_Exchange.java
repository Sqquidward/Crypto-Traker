package com.example.test_2.Stock_exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.test_2.Clients.ApiClient_Stock;
import com.example.test_2.Clients.ApiInterface;
import com.example.test_2.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Stock_Exchange extends AppCompatActivity {
    List<stock_exchange> movieList;
    RecyclerView recyclerView;
    RVAdapter_stock_exchange recyclerAdapter;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_exchange);
        movieList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_ex);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RVAdapter_stock_exchange(getApplicationContext(),movieList);
        recyclerView.setAdapter(recyclerAdapter);

//        but = findViewById(R.id.but);
//
//        but.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), MainActivity2.class);
//                view.getContext().startActivity(intent);
//            }
//        });


        ApiInterface apiService = ApiClient_Stock.getClient().create(ApiInterface.class);
        Call<List<stock_exchange>> call = apiService.getStock_exhange();

        call.enqueue(new Callback<List<stock_exchange>>() {
            @Override
            public void onResponse(Call<List<stock_exchange>> call, Response<List<stock_exchange>> response) {
                movieList = response.body();
                Log.d("TAG","Response = "+movieList);
                recyclerAdapter.setMovieList(movieList);
            }

            @Override
            public void onFailure(Call<List<stock_exchange>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }
}