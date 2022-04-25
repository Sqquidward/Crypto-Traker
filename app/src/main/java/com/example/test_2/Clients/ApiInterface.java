package com.example.test_2.Clients;

import com.example.test_2.Stock_market.Stock_Activity_Init;
import com.example.test_2.Stock_market.Stock_Main_Init;
import com.example.test_2.Stock_exchange.stock_exchange;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    //https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=10&page=1&sparkline=false

    @GET("api/v3/coins/markets")
    Call<List<Stock_Main_Init>> getStock_Main(@Query("vs_currency") String vs_currency, @Query("order") String order,
                                              @Query("per_page") int per_page, @Query("page") int page, @Query("sparkline") boolean sparkline);

    @GET("api/v3/coins/markets")
    Call<List<Stock_Activity_Init>> getStock_Activity(@Query("vs_currency") String vs_currency, @Query("order") String order,
                                                      @Query("per_page") int per_page, @Query("page") int page, @Query("sparkline") boolean sparkline);

    @GET("api/v3/exchanges")
    Call<List<stock_exchange>> getStock_exhange();
}