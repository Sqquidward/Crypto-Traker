package com.example.test_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.test_2.Clients.ApiClient_Stock;
import com.example.test_2.Clients.ApiInterface;
import com.example.test_2.Register.Activity_Login;
import com.example.test_2.Register.User;
import com.example.test_2.Stock_exchange.Activity_Stock_Exchange;
import com.example.test_2.Stock_exchange.RVAdapter_stockexchange_activity;
import com.example.test_2.Stock_exchange.stock_exchange;
import com.example.test_2.Stock_market.RecyclerAdapter_Stock_Main;
import com.example.test_2.Stock_market.Stock_Main_Init;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

     Button Stock_Market, Stock_Exchange_btn;
    FirebaseAuth mAuth;
    List<Stock_Main_Init> StockList;
    RecyclerView recyclerView;
    RecyclerAdapter_Stock_Main recyclerAdapter;

    List<stock_exchange> exchangeList;
    RVAdapter_stockexchange_activity recyclerAdapter_stockexchange;
    TextView name, hello_name, email;
    ImageView icon_menu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        name = findViewById(R.id.name);
        hello_name = findViewById(R.id.hello_name);
        email = findViewById(R.id.email);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference("users").child(currentUser.getUid());
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                if (user!=null){
                    name.setText(user.name+" "+user.lastname);
                    hello_name.setText(user.name);
                    email.setText(user.email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        Stock_Exchange_btn = findViewById(R.id.stock_exchange);
        Stock_Exchange_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, Activity_Stock_Exchange.class);
            startActivity(intent);
        });

        icon_menu = findViewById(R.id.icon_menu);
        icon_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Setting.class);
                startActivity(intent);
            }
        });

        Stock_Market = findViewById(R.id.Stock_market);
        Stock_Market.setOnClickListener(view -> {
            Intent intent = new Intent(this, Stock_activity.class);
            startActivity(intent);
        });




        StockList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_market);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerAdapter = new RecyclerAdapter_Stock_Main(getApplicationContext(),StockList);
        recyclerView.setAdapter(recyclerAdapter);

        ApiInterface apiService_market = ApiClient_Stock.getClient().create(ApiInterface.class);
        Call<List<Stock_Main_Init>> call_market = apiService_market.getStock_Main("usd", "market_cap_desc", 25, 1, false);

        call_market.enqueue(new Callback<List<Stock_Main_Init>>() {
            @Override
            public void onResponse(Call<List<Stock_Main_Init>> call, Response<List<Stock_Main_Init>> response) {
                StockList = response.body();
                Log.d("TAG","Response = "+StockList);
                recyclerAdapter.setMovieList(StockList);
            }

            @Override
            public void onFailure(Call<List<Stock_Main_Init>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview_exchange);
        LinearLayoutManager layoutManager_exchange = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager_exchange);
        recyclerAdapter_stockexchange = new RVAdapter_stockexchange_activity(getApplicationContext(),exchangeList);
        recyclerView.setAdapter(recyclerAdapter_stockexchange);





        ApiInterface apiService_exchange = ApiClient_Stock.getClient().create(ApiInterface.class);
        Call<List<stock_exchange>> call_exchange = apiService_exchange.getStock_exhange();

        call_exchange.enqueue(new Callback<List<stock_exchange>>() {
            @Override
            public void onResponse(Call<List<stock_exchange>> call, Response<List<stock_exchange>> response) {
                exchangeList = response.body();
                Log.d("TAG","Response = "+exchangeList);
                recyclerAdapter_stockexchange.setMovieList(exchangeList);
            }

            @Override
            public void onFailure(Call<List<stock_exchange>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(MainActivity.this, Activity_Login.class));}

    }

}