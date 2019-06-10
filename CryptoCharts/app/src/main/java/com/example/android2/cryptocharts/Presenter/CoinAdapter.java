package com.example.android2.cryptocharts.Presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android2.cryptocharts.R;

import java.util.ArrayList;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder>{
    private ArrayList<Coin> listOfCoins;
    private ArrayList<Coin> normalCoins;
    private ArrayList<Coin> favCoins = new ArrayList<>();

    public CoinAdapter(ArrayList<Coin> c) {

        listOfCoins = c;
        normalCoins = c;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView viewName;
        public TextView viewPrice;
        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            viewName  = itemView.findViewById(R.id.coinName);
            viewPrice = itemView.findViewById(R.id.coinPrice);
            imageView = itemView.findViewById(R.id.star_img);
        }
    }

    @Override
    public CoinAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View itemView = layoutInflater.inflate(R.layout.coin_info_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CoinAdapter.ViewHolder viewHolder, int position) {
        Coin c = listOfCoins.get(position);
        TextView textView = viewHolder.viewName;
        textView.setText(c.getName());
        textView = viewHolder.viewPrice;
        textView.setText(c.getPrice());
        final ImageView imageView = viewHolder.imageView;
        imageView.setImageResource(R.drawable.white_star);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = viewHolder.getAdapterPosition();
                Coin coin = listOfCoins.get(pos);
                System.out.println(coin.getName() + " clicked!!");
                if (coin.isFavourite()) {
                    imageView.setImageResource(R.drawable.white_star);
                    coin.toggleFavourite();
                    favCoins.remove(coin);
                    normalCoins.add(coin);
                } else {
                    imageView.setImageResource(R.drawable.yellow_star);
                    coin.toggleFavourite();
                    favCoins.add(coin);
                    normalCoins.remove(coin);
                }
                listOfCoins = favCoins;
                listOfCoins.addAll(normalCoins);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfCoins.size();
    }

}
