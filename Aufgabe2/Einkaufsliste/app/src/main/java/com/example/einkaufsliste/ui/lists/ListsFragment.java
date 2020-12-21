package com.example.einkaufsliste.ui.lists;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.R;
import com.example.einkaufsliste.models.BuyingList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Date;

public class ListsFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private BuyingListsAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
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
        return root;
    }

    private void inflateAddBuyingListDialog(@NonNull View root) {

        AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
        //builder.setTitle(R.string.buyinglist_creation);

        View viewInflated = LayoutInflater.from(root.getContext()).inflate(R.layout.add_buyinglist_dialog, (ViewGroup) getView(), false);
        builder.setView(viewInflated);

        builder.setPositiveButton(R.string.add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try{
                    EditText buyinglistName = viewInflated.findViewById(R.id.add_buyinglist_dialog_name);
                    DatePicker datePicker = viewInflated.findViewById(R.id.add_buyinglist_dialog_date);

                    String name = buyinglistName.getText().toString();
                    Date date = getDateFromDatePicker(datePicker);

                    if ((name != null) && (date != null)){
                        homeViewModel.getBuyingLists().add(new BuyingList(name, date));
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }
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
        builder.show();
    }

    private void initRecyclerView(View root) {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BuyingListsAdapter(homeViewModel.getBuyingLists());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public static Date getDateFromDatePicker(DatePicker datePicker){
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year =  datePicker.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

}