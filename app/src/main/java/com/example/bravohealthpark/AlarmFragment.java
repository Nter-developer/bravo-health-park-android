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

        monthYearText = v.findViewById(R.id.MonthYearText);
        recyclerView = v.findViewById(R.id.Date_RV);
        WeeklyCalendarUtil.selectedDate = LocalDate.now();

        setMonthView();
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM");
        return date.format(formatter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(){
        //년월 텍스트뷰 셋팅
        monthYearText.setText(monthYearFromDate(WeeklyCalendarUtil.selectedDate));
        //해당 월 날짜 가져오기
        ArrayList<LocalDate> dayList = daysInMonthArray(WeeklyCalendarUtil.selectedDate);
        //어뎁터 데이터 적용
        WeeklyCalendarAdapter adapter = new WeeklyCalendarAdapter(dayList);
        //레이아웃 설정(열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(), 7);
        //레이아웃 적용
        recyclerView.setLayoutManager(manager);
        //어뎁터 적용
        recyclerView.setAdapter(adapter);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<LocalDate> daysInMonthArray(LocalDate date){

        ArrayList<LocalDate> dayList = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        //해당 월 마지막 날짜 가져오기(예 28, 30, 31)
        int lastDay = yearMonth.lengthOfMonth();
        //해당 월의 첫 번째 날 가져오기(예 5월1일)
        LocalDate firstDay = WeeklyCalendarUtil.selectedDate.withDayOfMonth(1);
        //첫 번째 날 요일 가져오기(월:1 , 일:7)
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        //날짜 생성
        for(int i = 1; i < 8; i++){

            if( i <= dayOfWeek || i > lastDay + dayOfWeek){

                dayList.add(null);
            }else{

                dayList.add(LocalDate.of(WeeklyCalendarUtil.selectedDate.getYear(), WeeklyCalendarUtil.selectedDate.getMonth(),
                        i - dayOfWeek));
            }
        }
        /*for(int i = 1; i < 42; i++){

            if( i <= dayOfWeek || i > lastDay + dayOfWeek){

                dayList.add(null);
            }else{
                dayList.add(LocalDate.of(SmallCalendarUtil.selectedDate.getYear(), SmallCalendarUtil.selectedDate.getMonth(),
                        i - dayOfWeek));
            }
        }*/
        return dayList;
    }

}