package com.example.test_2.Stock_exchange;


import com.google.gson.annotations.SerializedName;

public class stock_exchange {
    @SerializedName("name")
    private String name;

    @SerializedName("year_established")
    private String year;

    @SerializedName("country")
    private String country;

    @SerializedName("url")
    private String url;

    @SerializedName("image")
    private String image;

    @SerializedName("trust_score")
    private String trust_score;

    @SerializedName("trade_volume_24h_btc")
    private String trade_volume_24h;

    public stock_exchange(String name, String year, String country, String url, String image, String trust_score, String trade_volume_24h) {
        this.name = name;
        this.year = year;
        this.country = country;
        this.url = url;
        this.image = image;
        this.trust_score = trust_score;
        this.trade_volume_24h = trade_volume_24h;
    }

    public String getTrade_volume_24h() {
        return trade_volume_24h;
    }

    public void setTrade_volume_24h(String trade_volume_24h) {
        this.trade_volume_24h = trade_volume_24h;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTrust_score() {
        return trust_score;
    }

    public void setTrust_score(String trust_score) {
        this.trust_score = trust_score;
    }
}
