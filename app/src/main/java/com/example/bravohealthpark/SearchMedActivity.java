package com.example.bravohealthpark;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.bravohealthpark.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class SearchMedActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ListView MedListView;
    private SearchAdapter adapter;
    private List<String> list;
    private ArrayList<String> arraylist;
    private SearchView SearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_med);

        MedListView = findViewById(R.id.MedListView);
        SearchView = findViewById(R.id.SearchView);

        list = new ArrayList<String>();

        settingList();

        arraylist = new ArrayList<String>();
        arraylist.addAll(list);

        adapter = new SearchAdapter(list, this);

        MedListView.setAdapter(adapter);

        SearchView.setOnQueryTextListener(this);
    }

    private void search(String query) {
        list.clear();
        if (query.length() == 0) {
            list.addAll(arraylist);
        } else {
            for (String item : arraylist) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    list.add(item);
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        search(newText);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        search(query);
        return true;
    }

    private void settingList() {
        list.add("약1");
        list.add("약2");
        list.add("약3");
        // TODO: 데이터를 추가하거나 API를 통해 데이터를 가져옴.
    }
}