package com.taner.retrofitjava.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("title")
    public String currency;

    @SerializedName("price")
    public String price;

}
