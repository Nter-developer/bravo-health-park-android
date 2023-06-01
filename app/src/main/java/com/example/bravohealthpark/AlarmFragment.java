package com.example.bravohealthpark;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AlarmFragment extends Fragment {
    private TextView btn_1;
    private TextView btn_2;
    private boolean btnState = false;
    private LinearLayout GoCalendar;
    private TextView monthYearText;
    private RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_alarm, container, false);
        GoCalendar = v.findViewById(R.id.GoCalendar);
        btn_1 = v.findViewById(R.id.Alarm_List_Btn_1);
        btn_2 = v.findViewById(R.id.Alarm_List_Btn_2);



        GoCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalendarActivity.class);
                startActivity(intent);
            }
        });
        btn_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(btnState)
                {
                    btnState = true;
                    //btn_1.setText("OFF");
                    btn_1.setTypeface(null, Typeface.NORMAL);
                    btn_1.setTextColor(Color.parseColor("#000000"));
                    btn_1.setBackgroundResource(R.drawable.shape_none_stroke);
                    //btn_2.setText("ON");
                    btn_2.setTypeface(null, Typeface.BOLD);
                    btn_2.setTextColor(Color.parseColor("#00D1CB"));
                    btn_2.setBackgroundResource(R.drawable.layout_bottom_line);
                }
                else
                {
                    btnState = false;
                    //btn_1.setText("ON");
                    btn_1.setTypeface(null, Typeface.BOLD);
                    btn_1.setTextColor(Color.parseColor("#00D1CB"));
                    btn_1.setBackgroundResource(R.drawable.layout_bottom_line);
                    //btn_2.setText("OFF");
                    btn_2.setTypeface(null, Typeface.NORMAL);
                    btn_2.setTextColor(Color.parseColor("#000000"));
                    btn_2.setBackgroundResource(R.drawable.shape_none_stroke);
                }
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(btnState)
                {
                    btnState = true;
                    btn_1.setTypeface(null, Typeface.BOLD);
                    btn_1.setTextColor(Color.parseColor("#00D1CB"));
                    btn_1.setBackgroundResource(R.drawable.layout_bottom_line);
                    //btn_2.setText("OFF");
                    btn_2.setTypeface(null, Typeface.NORMAL);
                    btn_2.setTextColor(Color.parseColor("#000000"));
                    btn_2.setBackgroundResource(R.drawable.shape_none_stroke);
                }
                else
                {
                    btnState = false;
                    btn_1.setTypeface(null, Typeface.NORMAL);
                    btn_1.setTextColor(Color.parseColor("#000000"));
                    btn_1.setBackgroundResource(R.drawable.shape_none_stroke);
                    //ex_2.setText("ON");
                    btn_2.setTypeface(null, Typeface.BOLD);
                    btn_2.setTextColor(Color.parseColor("#00D1CB"));
                    btn_2.setBackgroundResource(R.drawable.layout_bottom_line);
                }
            }
        });

        return v;

    }



}