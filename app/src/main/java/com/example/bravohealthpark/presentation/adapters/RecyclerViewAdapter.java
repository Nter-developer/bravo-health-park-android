package com.example.bravohealthpark.presentation.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.alarm.AlarmItem;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.AdapterViewHolder> {

    private ArrayList<AlarmItem> arrayListAlarm;

    public RecyclerViewAdapter(ArrayList<AlarmItem> arrayList) {
        this.arrayListAlarm = arrayList;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alarm, parent,false);
        AdapterViewHolder holder = new AdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.textViewTime.setText(arrayListAlarm.get(position).getTime());
        holder.textViewMediName.setText(arrayListAlarm.get(position).getMediName());
    }

    @Override
    public int getItemCount() {
        return (null != arrayListAlarm ? arrayListAlarm.size() :0);
    }

    public static class AdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTime, textViewMediName;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = (TextView) itemView.findViewById(R.id.textview_time);
            textViewMediName = (TextView) itemView.findViewById(R.id.textview_medi_name);
        }
    }
}