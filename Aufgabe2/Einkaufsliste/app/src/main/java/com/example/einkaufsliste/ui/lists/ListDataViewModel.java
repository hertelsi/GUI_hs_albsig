package com.example.einkaufsliste.ui.lists;

import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.ui.sampleData.SampleData;

import java.util.ArrayList;

public class ListDataViewModel extends ViewModel {

    private ArrayList<String> ListData;

    public ListDataViewModel(){
        ListData = new ArrayList<>();
        ListData.addAll(SampleData.getListData());

    }
    public ArrayList<String> getListData() { return ListData;}
}
