package com.example.bravohealthpark.presentation.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.alarm.AlarmItem;
import com.example.bravohealthpark.domain.alarm.DoseState;
import com.example.bravohealthpark.presentation.adapters.RecyclerViewAdapter;

import java.util.ArrayList;

public class AlarmFragment extends Fragment {

    private ArrayList<AlarmItem> arrayListAlarm;
    private RecyclerViewAdapter recyclerViewAdapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);

        arrayListAlarm = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(arrayListAlarm);
        load();
        RecyclerView recyclerViewAlarm = v.findViewById(R.id.recyclerView_alarm);
        recyclerViewAlarm.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerViewAlarm.setLayoutManager(linearLayoutManager);
        
        recyclerViewAlarm.setAdapter(recyclerViewAdapter);


        Log.i("LOAD", "SUCCESS");
        return v;
    }

    @SuppressLint("NotifyDataSetChanged")
    void load(){
        AlarmItem alarmItem1 = new AlarmItem("오전 9시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem1);
        AlarmItem alarmItem2 = new AlarmItem("오후 1시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem2);
        AlarmItem alarmItem3 = new AlarmItem("오후 7시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem3);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}