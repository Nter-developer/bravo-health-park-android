package com.example.bravohealthpark.presentation.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bravohealthpark.domain.calendar.CalendarAdapter;
import com.example.bravohealthpark.domain.calendar.CalendarUtil;
import com.example.bravohealthpark.R;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CalendarActivity extends AppCompatActivity{

    TextView monthYearText; //년월 텍스트뷰

    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        //초기화
        monthYearText = findViewById(R.id.MonthYearText);
        ImageButton preBtn = findViewById(R.id.Pre_Btn);
        ImageButton nextBtn = findViewById(R.id.Next_Btn);
        recyclerView = findViewById(R.id.recyclerView);

        //현재 날짜
        CalendarUtil.selectedDate = LocalDate.now();

        //화면 설정
        setMonthView();

        //이전 달 버튼 이벤트
        preBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //-1한 월을 넣어준다. (2월 -> 1월)
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.minusMonths(1);
                setMonthView();
            }
        });

        //다음 달 버튼 이벤트
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //+1한 월을 넣어준다.(2월 -> 3월)
                CalendarUtil.selectedDate = CalendarUtil.selectedDate.plusMonths(1);
                setMonthView();
            }
        });
    }//onCreate

    //날짜 타입 설정(2023 5월)
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String monthYearFromDate(LocalDate date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY년 MM월");

        return date.format(formatter);
    }


    //화면 설정
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void setMonthView(){

        //년월 텍스트뷰 셋팅
        monthYearText.setText(monthYearFromDate(CalendarUtil.selectedDate));

        //해당 월 날짜 가져오기
        ArrayList<LocalDate> dayList = daysInMonthArray(CalendarUtil.selectedDate);

        //어뎁터 데이터 적용
        CalendarAdapter adapter = new CalendarAdapter(dayList);

        //레이아웃 설정(열 7개)
        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);

        //레이아웃 적용
        recyclerView.setLayoutManager(manager);

        //어뎁터 적용
        recyclerView.setAdapter(adapter);
    }

    //날짜 생성
    @RequiresApi(api = Build.VERSION_CODES.O)
    private ArrayList<LocalDate> daysInMonthArray(LocalDate date){

        ArrayList<LocalDate> dayList = new ArrayList<>();

        YearMonth yearMonth = YearMonth.from(date);

        //해당 월 마지막 날짜 가져오기(예 28, 30, 31)
        int lastDay = yearMonth.lengthOfMonth();

        //해당 월의 첫 번째 날 가져오기(예 4월1일)
        LocalDate firstDay = CalendarUtil.selectedDate.withDayOfMonth(1);

        //첫 번째 날 요일 가져오기(월:1 , 일:7)
        int dayOfWeek = firstDay.getDayOfWeek().getValue();

        //날짜 생성
        for(int i = 1; i < 42; i++){

            if( i <= dayOfWeek || i > lastDay + dayOfWeek){

                dayList.add(null);
            }else{
                dayList.add(LocalDate.of(CalendarUtil.selectedDate.getYear(), CalendarUtil.selectedDate.getMonth(),
                        i - dayOfWeek));
            }
        }

        return dayList;
    }
}