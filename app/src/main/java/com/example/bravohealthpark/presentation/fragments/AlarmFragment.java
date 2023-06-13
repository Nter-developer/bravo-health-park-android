package com.example.bravohealthpark.presentation.fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.alarm.AlarmItem;
import com.example.bravohealthpark.domain.alarm.DoseState;
import com.example.bravohealthpark.domain.alarm.Meal;
import com.example.bravohealthpark.domain.alarm.dto.CreateAlarmResponse;
import com.example.bravohealthpark.domain.alarm.service.AlarmRetrofitService;
import com.example.bravohealthpark.infra.retrofit.RetrofitClient;
import com.example.bravohealthpark.presentation.adapters.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AlarmFragment extends Fragment {

    private ArrayList<AlarmItem> arrayListAlarm;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerViewAlarm;
    private LinearLayoutManager linearLayoutManager;
    private AlarmRetrofitService alarmRetrofitService;
    private Button button;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        initComponents(v);
        setRecyclerViewAlarmAdapter(container);
        alarmRetrofitService = RetrofitClient.getApiService(AlarmRetrofitService.class);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        testSendCreateAlarm();
    }

    private void testSendCreateAlarm() {
        List<String> listTimes = new ArrayList<>();
        listTimes.add("09:00");
        listTimes.add("13:00");
        listTimes.add("20:00");

        Call<List<CreateAlarmResponse>> call = alarmRetrofitService.SendCreateAlarm("master", Meal.AFTER, listTimes);
        call.enqueue(new Callback<List<CreateAlarmResponse>>() {
            @Override
            public void onResponse(Call<List<CreateAlarmResponse>> call, Response<List<CreateAlarmResponse>> response) {

            }

            @Override
            public void onFailure(Call<List<CreateAlarmResponse>> call, Throwable t) {

            }
        });
    }

    private void initComponents(View v) {
        arrayListAlarm = new ArrayList<>();
        recyclerViewAdapter = new RecyclerViewAdapter(arrayListAlarm);
        recyclerViewAlarm = v.findViewById(R.id.recyclerView_alarm);
    }
    private void setRecyclerViewAlarmAdapter(ViewGroup container) {
        setAdapterAlarmData();
        setAdapterDecoAndManager(container);
    }

    private void setAdapterDecoAndManager(ViewGroup container) {
        recyclerViewAlarm.addItemDecoration(new DividerItemDecoration(container.getContext(), DividerItemDecoration.VERTICAL));
        linearLayoutManager = new LinearLayoutManager(container.getContext());
        recyclerViewAlarm.setLayoutManager(linearLayoutManager);
        recyclerViewAlarm.setAdapter(recyclerViewAdapter);
    }

    @SuppressLint("NotifyDataSetChanged")
    void setAdapterAlarmData(){
        AlarmItem alarmItem1 = new AlarmItem("오전 9시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem1);
        AlarmItem alarmItem2 = new AlarmItem("오후 1시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem2);
        AlarmItem alarmItem3 = new AlarmItem("오후 7시","소염제", DoseState.NON_DOSE);
        arrayListAlarm.add(0, alarmItem3);
        recyclerViewAdapter.notifyDataSetChanged();
    }
}