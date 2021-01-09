package com.example.einkaufsliste.models;


import com.example.einkaufsliste.ui.lists.ListDataAdapter;

import java.util.ArrayList;
import java.util.List;

public class ListData {
    private static List<ListData> Liste;
    private ListDataAdapter adapter;
    private String text;
    private int menge;
    private int id;

    public ListData ( String text, int menge){
        this.text = text;
        this.menge = menge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMenge(){return menge;}






}
