package com.example.einkaufsliste.ui.lists;

import androidx.lifecycle.ViewModel;

import com.example.einkaufsliste.Repository;
import com.example.einkaufsliste.models.ListData;
import com.example.einkaufsliste.rest.InfrastructureWebservice;
import com.example.einkaufsliste.ui.sampleData.SampleData;

import java.util.ArrayList;
import java.util.List;

public class ListDataViewModel extends ViewModel {

    private List<ListData> ListData;

    public ListDataViewModel(){
        ListData = new ArrayList<>();
        ListData.addAll(SampleData.getListData());

    }
    public List<ListData> getListData() { return ListData;}

    public int addUser(String username){
        InfrastructureWebservice service = new InfrastructureWebservice();
        return service.addUserToBuyingList(Repository.getInstance().getCurrentBuyingListId(), username);
    }
}
