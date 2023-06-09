package com.example.bravohealthpark.presentation.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.medication.Time;

import java.util.ArrayList;

public class AdapterActivity extends BaseAdapter {

    public ArrayList<Time> listviewItem = new ArrayList<Time>();
    private ArrayList<Time> arrayList = listviewItem;   //백업 arrayList

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.alarm_list, parent, false);

            TextView hourText = (TextView)convertView.findViewById(R.id.textTime1);
            TextView minuteText = (TextView)convertView.findViewById(R.id.textTime2);
            TextView am_pm = (TextView)convertView.findViewById(R.id.am_pm);

            holder.hourText = hourText;
            holder.minuteText = minuteText;
            holder.am_pm = am_pm;


            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder)convertView.getTag();
        }

        Time time = arrayList.get(position);
        holder.am_pm.setText(time.getAm_pm());
        holder.hourText.setText(time.getHour()+ "시");
        holder.minuteText.setText(time.getMinute()+ "분");

        return convertView;
    }

    public void addItem(int hour, int minute, String am_pm) {
        Time time = new Time();

        time.setHour(hour);
        time.setMinute(minute);
        time.setAm_pm(am_pm);

        listviewItem.add(time);
    }
    //List 삭제 method
    public void removeItem(int position) {
        if(listviewItem.size() < 1) {

        }
        else {
            listviewItem.remove(position);
        }
    }

    public void removeItem() {
        if(listviewItem.size() < 1) {

        }
        else {
            listviewItem.remove(listviewItem.size()-1);
        }
    }

    static class ViewHolder {
        TextView hourText, minuteText, am_pm;
    }

}
