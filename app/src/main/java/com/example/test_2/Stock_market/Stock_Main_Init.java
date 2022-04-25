package com.example.test_2.Stock_market;

import com.google.gson.annotations.SerializedName;

public class Stock_Main_Init {

    @SerializedName("name")
    private String title;

    @SerializedName("current_price")
    private String price;

    @SerializedName("high_24h")
    private String high_24;

    public Stock_Main_Init(String title, String price, String high_24) {
        this.title = title;
        this.price = price;
        this.high_24 = high_24;
    }

    public String getTitle() {
        return title;
    }

    public String getPrice() {
        return price;
    }



    public String getHigh_24() {
        return high_24;
    }


}