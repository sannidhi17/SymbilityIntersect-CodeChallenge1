package com.example.android2.cryptocharts.Presenter;

public class Coin {

    private String mName;
    private String mPrice;
    private boolean isFavourite;

    public Coin(String name, String price, boolean fav) {
        mName = name;
        mPrice = price;
        isFavourite = fav;
    }

    public String getName() {
        return mName;
    }

    public String getPrice() {
        return mPrice;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void toggleFavourite() {
        isFavourite = !isFavourite;
    }

}
