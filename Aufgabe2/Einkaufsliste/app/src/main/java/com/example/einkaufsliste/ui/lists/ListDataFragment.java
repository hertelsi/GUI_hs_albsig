package com.example.einkaufsliste.ui.lists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.einkaufsliste.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListDataFragment extends Fragment {
    private FloatingActionButton fab1;
    private ArrayList<String> content;
    private ListDataAdapter adapter;
    private EditText edit_text_ListData;
    private ListDataViewModel homeViewModel;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(this).get(ListDataViewModel.class);
        View root = inflater.inflate(R.layout.fragment_listdata, container, false);

        recyclerView = root.findViewById(R.id.rec_view_first);
        initRecyclerView(root);

        adapter = new ListDataAdapter(content);

        fab1 = root.findViewById(R.id.fabListData);
        edit_text_ListData = root.findViewById(R.id.edit_text_ListData);
        // fab fuegt Editier-Inhalt der Liste hinzu
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.notifyDataSetChanged();
                homeViewModel.getListData().add(edit_text_ListData.getText().toString());
                adapter.notifyDataSetChanged();// Model benachrichtigen
            }
        });
        return root;
    }
    private void initRecyclerView(View root) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(root.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListDataAdapter(homeViewModel.getListData());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}
