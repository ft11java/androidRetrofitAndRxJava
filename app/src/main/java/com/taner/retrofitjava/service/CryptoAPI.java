package com.taner.retrofitjava.service;

import com.taner.retrofitjava.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    //https://fakestoreapi.com/products



    @GET("products")
    Observable<List<CryptoModel>> getData();

    //Call<List<CryptoModel>> getData();


}
