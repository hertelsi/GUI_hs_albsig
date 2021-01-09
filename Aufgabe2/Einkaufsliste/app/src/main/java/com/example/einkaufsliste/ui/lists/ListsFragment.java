package com.example.einkaufsliste.ui.lists;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.MainActivity;
import com.example.einkaufsliste.R;
import com.example.einkaufsliste.models.BuyingList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Collection;

public class ListsFragment extends Fragment {

    private ListsViewModel listsViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private BuyingListsAdapter adapter;
    private AlertDialog.Builder builder;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        listsViewModel = new ViewModelProvider(getActivity()).get(ListsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lists, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        initRecyclerView(root);

        floatingActionButton = root.findViewById(R.id.fragment_list_fab);
        // fab fuegt Editier-Inhalt der Liste hinzu
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inflateAddBuyingListDialog(view);
                adapter.notifyDataSetChanged();
            }
        });
        preapareAndBuildDialog(root);
        return root;
    }

    private void inflateAddBuyingListDialog(@NonNull View root) {
        builder.show();
    }

    private void preapareAndBuildDialog(View root){
        builder = new AlertDialog.Builder(root.getContext());
        View viewInflated = LayoutInflater.from(root.getContext()).inflate(R.layout.add_buyinglist_dialog, (ViewGroup) getView(), false);
        builder.setView(viewInflated);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    EditText buyinglistName = viewInflated.findViewById(R.id.add_buyinglist_dialog_name);
                    DatePicker datePicker = viewInflated.findViewById(R.id.add_buyinglist_dialog_date);
                    listsViewModel.addOneBuyingList(buyinglistName, datePicker);
                    adapter.notifyDataSetChanged();
                    dialog.cancel();
                } catch (Exception e){
                    Log.e("criticalErrorInAddBuyingListFunctionality", e.toString());
                }
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }

    private void initRecyclerView(View root) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BuyingListsAdapter(((MainActivity)getActivity()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        listsViewModel.getAllbuyingLists().observe(getViewLifecycleOwner(), new Observer<Collection<BuyingList>>() {
            @Override
            public void onChanged(Collection<BuyingList> buyingLists) {
                adapter.setBuyingLists(buyingLists);
            }
        });
    }
}