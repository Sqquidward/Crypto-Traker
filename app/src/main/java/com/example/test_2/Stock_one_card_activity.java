package com.example.test_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Stock_one_card_activity extends AppCompatActivity {


    TextView text, price, symbol, high_24, low_24, Photo, atl, atl_change_percentage, circulating_supply, market_cap_change_24h, market_cap_rank, price_change_24h, total_volume;
    ImageView image;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_one_card);
        String name = getIntent().getStringExtra("name");
        String getprice = getIntent().getStringExtra("price");
        String getSymbol = getIntent().getStringExtra("symbol");
        String getHigh_24 = getIntent().getStringExtra("high_24");
        String getLow_24 = getIntent().getStringExtra("low_24");
        String getStrPhoto = getIntent().getStringExtra("photo");

        String getAtl = getIntent().getStringExtra("atl");
        String getAtl_change_percentage = getIntent().getStringExtra("atl_change_percentage");
        String getCirculating_supply = getIntent().getStringExtra("circulating_supply");
        String getMarket_cap_change_24h = getIntent().getStringExtra("market_cap_change_24h");
        String getMarket_cap_rank = getIntent().getStringExtra("market_cap_rank");
        String getPrice_change_24h = getIntent().getStringExtra("price_change_24h");
        String getTotal_volume = getIntent().getStringExtra("total_volume");




        text = findViewById(R.id.name);
        symbol = findViewById(R.id.symbol);
        price = findViewById(R.id.price);
        image = findViewById(R.id.imageView2);
        high_24 = findViewById(R.id.high_24);
        low_24 = findViewById(R.id.low_24);
        atl = findViewById(R.id.atl);
        atl_change_percentage = findViewById(R.id.atl_change_percentage);
        circulating_supply = findViewById(R.id.circulating_supply);
        market_cap_change_24h = findViewById(R.id.cap_change_24h);
        market_cap_rank = findViewById(R.id.market_cap_rank_layout);
        price_change_24h = findViewById(R.id.price_change_24h);
        total_volume = findViewById(R.id.total_volume);

        text.setText(name);
        price.setText(getprice);
        symbol.setText(getSymbol);
        high_24.setText(getHigh_24);
        low_24.setText(getLow_24);
        Glide.with(Stock_one_card_activity.this)
                .load(getStrPhoto)
                .into(image);
        atl.setText(getAtl);
        atl_change_percentage.setText(getAtl_change_percentage);
        circulating_supply.setText(getCirculating_supply);
        market_cap_change_24h.setText(getMarket_cap_change_24h);
        market_cap_rank.setText(getMarket_cap_rank);
        price_change_24h.setText(getPrice_change_24h);
        total_volume.setText(getTotal_volume);


    }
}