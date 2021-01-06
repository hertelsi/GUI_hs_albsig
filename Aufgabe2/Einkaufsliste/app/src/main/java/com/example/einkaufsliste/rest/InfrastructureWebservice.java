package com.example.einkaufsliste.rest;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.Article;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URLConnection;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfrastructureWebservice {
    private String URL = "http://" +  Repository.getInstance().getIpAddress() + ":8080/EinkaufsListeRestProject/rest/buyingList";

    private GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson = gsonBuilder.create();

    private java.net.URL url;
    private URLConnection connection;
    private HttpURLConnection httpConnection;

    private OkHttpClient client = new OkHttpClient();

    private String urlString;


    public BuyingList getBuyingList(long id) throws NoSuchRowException {
        urlString = URL + "/buyingLists/" + id;
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String output;
            BuyingList buyingList = null;
            if ((output = response.body().string()) != null) {
                buyingList = gson.fromJson(output, BuyingList.class);
            }
            return buyingList;
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        } catch (com.google.gson.JsonSyntaxException e) {
            throw new NoSuchRowException();
        }
        return null;
    }

    public User register(User u) throws SocketTimeoutException {
        urlString = URL + "/register";
        OkHttpClient client = new OkHttpClient();
        String json = gson.toJson(u);
        RequestBody body = new FormBody.Builder()
                .add("name", u.getName())
                .add("password", u.getPassword())
                .build();
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseMsg = response.body().string();
            if (response.code() == 200) {
                u = gson.fromJson(responseMsg, User.class);
                return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(e.getClass() == SocketTimeoutException.class)
            {
                throw new SocketTimeoutException();
            }
        }
        return null;
    }

    public User login(User u) throws SocketTimeoutException {
        urlString = URL + "/login";
        OkHttpClient client = new OkHttpClient();
        String json = gson.toJson(u);
        RequestBody body = new FormBody.Builder()
                .add("name", u.getName())
                .add("password", u.getPassword())
                .build();
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseMsg = response.body().string();
            if (response.code() == 200) {
                u = gson.fromJson(responseMsg, User.class);
                return u;
            }
        } catch (IOException e) {
            e.printStackTrace();
            if(e.getClass() == SocketTimeoutException.class)
            {
                throw new SocketTimeoutException();
            }
        }
        return null;
    }

    public int addUserToBuyingList(int buyingListId, String username){
        urlString = URL + "/buyingLists/user";
        OkHttpClient client = new OkHttpClient();
        User user = Repository.getInstance().getUser();
        RequestBody body = new FormBody.Builder()
                .add("buyingListId", String.valueOf(buyingListId))
                .add("username", username)
                .build();
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            if (response.code() == 200) {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addBuyingList(BuyingList b){
        urlString = URL + "/buyingLists";
        OkHttpClient client = new OkHttpClient();
        User user = Repository.getInstance().getUser();
        RequestBody body = new FormBody.Builder()
                .add("password", user.getPassword())
                .add("username", user.getName())
                .add("name", b.getName())
                .add("creationDate", String.valueOf(b.getCreationDate().getTime()))
                .add("buyingDate", String.valueOf(b.getBuyingDate().getTime()))
                .add("shopName", b.getShop().getName())
                .add("shopLocation", b.getShop().getLocation())
                .build();
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseMsg = response.body().string();
            if (response.code() == 200) {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int addArticle(Article a){
        urlString = URL + "/articles";
        OkHttpClient client = new OkHttpClient();
        User user = Repository.getInstance().getUser();
        RequestBody body = new FormBody.Builder()
                .add("name", a.getName())
                .add("unit", a.getUnit())
                .add("amount", String.valueOf(a.getAmount()))
                .add("buyingListId", String.valueOf(a.getBuyingList().getId()))
                .build();
        Request request = new Request.Builder()
                .url(urlString)
                .post(body)
                .build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            String responseMsg = response.body().string();
            if (response.code() == 200) {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int deleteArticle(Article a){
        urlString = URL + "/articles/" + a.getId();
        Request request = new Request.Builder()
                .url(urlString)
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (response.code() == 200) {
                return 0;
            }
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        }
        return -1;
    }

    public int deleteBuyingList(BuyingList b){
        urlString = URL + "/buyingLists/" + b.getId();
        Request request = new Request.Builder()
                .url(urlString)
                .delete()
                .build();
        try {
            Response response = client.newCall(request).execute();
            String responseString = response.body().string();
            if (response.code() == 200) {
                return 0;
            }
        } catch (IOException e) { // zu newCall(request).execute() und response.body().string();
            e.printStackTrace();
        }
        return -1;
    }




}
