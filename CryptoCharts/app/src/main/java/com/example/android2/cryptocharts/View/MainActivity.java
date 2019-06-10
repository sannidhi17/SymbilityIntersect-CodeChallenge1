package com.example.android2.cryptocharts.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android2.cryptocharts.Presenter.Coin;
import com.example.android2.cryptocharts.Presenter.CoinAdapter;
import com.example.android2.cryptocharts.Model.FetchData;
import com.example.android2.cryptocharts.Model.FetchDataCallbackInterface;
import com.example.android2.cryptocharts.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements FetchDataCallbackInterface {

    private ArrayList<Coin> coins = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.listOfCoins);
        new FetchData("", this).execute();
        System.out.println(coins.size());
        CoinAdapter coinAdapter = new CoinAdapter(coins);
        recyclerView.setAdapter(coinAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void fetchDataCallback(ArrayList<Coin> result) {
        coins = result;
        CoinAdapter coinAdapter = new CoinAdapter(coins);
        recyclerView.setAdapter(coinAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
