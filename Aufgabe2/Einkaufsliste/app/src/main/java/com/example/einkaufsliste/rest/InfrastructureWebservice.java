package com.example.einkaufsliste.rest;

import android.util.Log;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.Article;
import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class InfrastructureWebservice {
    private String frontURL = "http://";
    private String lastURL = ":8080/EinkaufsListeRestProject/rest/buyingList";

    private Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, (JsonDeserializer) (json, typeOfT, context) -> new Date(json.getAsLong())).create();

    private java.net.URL url;
    private URLConnection connection;
    private HttpURLConnection httpConnection;
    private OkHttpClient client = new OkHttpClient();
    private String urlString;

    public User register(User u) throws SocketTimeoutException {
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/register";
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
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/login";
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
                User newUser = gson.fromJson(responseMsg, User.class);
                Log.i("User", u.toString());
                Log.i("UserResponse", responseMsg);
                return newUser;
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

    public int addUserToBuyingList(long buyingListId, String username){
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/buyingLists/user";
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
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/buyingLists";
        OkHttpClient client = new OkHttpClient();
        User user = Repository.getInstance().getUser();
        RequestBody body = new FormBody.Builder()
                .add("password", user.getPassword())
                .add("username", user.getName())
                .add("name", b.getName())
                .add("creationDate", String.valueOf(b.getCreationDate().getTime()))
                .add("buyingDate", String.valueOf(b.getBuyingDate().getTime()))
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
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/articles";
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
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/articles/" + a.getId();
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
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/buyingLists/" + b.getId();
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

    public int changeDate(int id, Date date){
        urlString = frontURL + Repository.getInstance().getIpAddress() + lastURL + "/buyingLists/date";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("buyingListId", String.valueOf(id))
                .add("newBuyingDate", String.valueOf(date.getTime()))
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

}
