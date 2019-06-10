package com.example.android2.cryptocharts.Model;

import com.example.android2.cryptocharts.Presenter.Coin;

import java.util.ArrayList;

public interface FetchDataCallbackInterface {

    public void fetchDataCallback (ArrayList<Coin> result);

}
