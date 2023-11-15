package com.taner.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taner.retrofitjava.R;
import com.taner.retrofitjava.adapter.RecyclerViewAdapter;
import com.taner.retrofitjava.model.CryptoModel;
import com.taner.retrofitjava.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private String BASE_URL = "https://fakestoreapi.com/";
    Retrofit retrofit;
    RecyclerView recyclerView;

    RecyclerViewAdapter recyclerViewAdapter;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        recyclerView = findViewById(R.id.recyclerView);


        //Retrofit and JSON
        Gson gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        loadData();

    }

    private void loadData() {
        CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));

        /* Retrofit

        Call<List<CryptoModel>> call = cryptoAPI.getData();

        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()) {
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    //RecyclerView
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels);
                    recyclerView .setAdapter(recyclerViewAdapter);

                    for (CryptoModel cryptoModel : cryptoModels) {
                        System.out.println(cryptoModel.currency+":  "+cryptoModel.price);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

*/
    }

    private void handleResponse(List<CryptoModel> cryptoModelList){


        cryptoModels = new ArrayList<>(cryptoModelList);

        //RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter=new RecyclerViewAdapter(cryptoModels);
        recyclerView .setAdapter(recyclerViewAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }
}