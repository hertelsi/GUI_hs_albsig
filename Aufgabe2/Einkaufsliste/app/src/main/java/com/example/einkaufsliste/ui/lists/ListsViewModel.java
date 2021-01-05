package com.example.einkaufsliste.ui.lists;

import android.util.Log;

import com.example.einkaufsliste.models.BuyingList;
import com.example.einkaufsliste.ui.sampleData.SampleData;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.ViewModel;

public class ListsViewModel extends ViewModel {

    private List<BuyingList> buyingLists;

    public ListsViewModel() {
        buyingLists = new ArrayList<>();
        buyingLists.addAll(SampleData.getBuyingLists());

        for (BuyingList buyingList : buyingLists){
            Log.i("plainOldBuyingLists", buyingList.toString());
        }

    }

    public List<BuyingList> getBuyingLists() {
        return buyingLists;
    }
}