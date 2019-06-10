package com.example.android2.cryptocharts.Model;

import android.os.AsyncTask;

import com.example.android2.cryptocharts.Presenter.Coin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchData extends AsyncTask<String, String, ArrayList<Coin>> {

    private HttpURLConnection httpConnection;
    private String urlstr;
    private URL url;
    private BufferedReader bufferedReader;
    private StringBuffer strBuffer;
    private String response;
    private FetchDataCallbackInterface callbackInterface;
    public ArrayList<Coin> coins = new ArrayList<>();

    public FetchData(String url, FetchDataCallbackInterface callbackInterface) {
        this.urlstr = url;
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected ArrayList<Coin> doInBackground(String... args) {
        try {
            url = new URL("https://min-api.cryptocompare.com/data/all/coinlist");
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            strBuffer = new StringBuffer();
            while ((response = bufferedReader.readLine()) != null) {
                strBuffer.append(response);
            }
            bufferedReader.close();
            httpConnection.disconnect();

            JSONObject jo = new JSONObject(strBuffer.toString());
            JSONObject ja = jo.getJSONObject("Data");
            JSONArray keys = ja.names();
            int size = keys.length();
            for (int i = 0; i < size; i++) {
                String name = ja.getJSONObject(keys.getString(i)).getString("Name");
                String price = getPrice(name);
                if (price != null) {
                    Coin c = new Coin(name, price, false);
                    coins.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coins;
    }

    private String getPrice(String name) {
        String ret = "";
        try {
            response = "";
            urlstr = "https://min-api.cryptocompare.com/data/price?fsym=" + name + "&tsyms=CAD";
            url = new URL(urlstr);
            httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));
            strBuffer = new StringBuffer();
            while ((response = bufferedReader.readLine()) != null) {
                strBuffer.append(response);
            }
            bufferedReader.close();
            httpConnection.disconnect();
            JSONObject jo = new JSONObject(strBuffer.toString());
            if (jo.has("CAD")) {
                ret = jo.getString("CAD");
            } else return null;
        } catch (Exception e) {
            System.out.println(name);
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void onPostExecute(ArrayList<Coin> result) {
        super.onPostExecute(result);
        this.callbackInterface.fetchDataCallback(coins);
    }

}
