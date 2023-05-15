package com.example.bravohealthpark;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TimePicker;

import com.example.myapplication.R;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MedRegistrationActivity extends AppCompatActivity {

    private EditText medName;
    private LinearLayout ParentLayout;
    private EditText StartDay, EndDay;
    private Button openCalStartBtn, openCalEndBtn;
    private CheckBox everydayBtn;
    private Button monBtn, tueBtn, wedBtn, thuBtn, friBtn, satBtn, sunBtn;
    private Button addTimeBtn;
    private ListView alarmListView;
    private AdapterActivity adapter;

    void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_registration);

        medName = findViewById(R.id.Med_Name);
        ParentLayout = findViewById(R.id.ParentLayout);
        StartDay = findViewById(R.id.StartDay);
        EndDay = findViewById(R.id.EndDay);
        openCalStartBtn = findViewById(R.id.OpenCal_Start);
        openCalEndBtn = findViewById(R.id.OpenCal_End);
        everydayBtn = findViewById(R.id.Check_Everyday);
        monBtn = findViewById(R.id.Check_Mon);
        tueBtn = findViewById(R.id.Check_Tue);
        wedBtn = findViewById(R.id.Check_Wed);
        thuBtn = findViewById(R.id.Check_Thu);
        friBtn = findViewById(R.id.Check_Fri);
        satBtn = findViewById(R.id.Check_Sat);
        sunBtn = findViewById(R.id.Check_Sun);
        addTimeBtn = findViewById(R.id.AddTime_Btn);
        alarmListView = findViewById(R.id.Alarm_ListView);
        alarmListView = (ListView) findViewById(R.id.Alarm_ListView);
        adapter = new AdapterActivity();
        alarmListView.setAdapter(adapter);

        alarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        addTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 현재 시간 구하기
                final Calendar currentTime = Calendar.getInstance();
                int hour = currentTime.get(Calendar.HOUR_OF_DAY);
                int minute = currentTime.get(Calendar.MINUTE);

                // TimePickerDialog 띄우기
                TimePickerDialog timePicker = new TimePickerDialog(MedRegistrationActivity.this, android.R.style.Theme_Holo_Light_Dialog_NoActionBar, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // 선택한 시간을 Adapter에 추가하여 ListView에 보여주기
                        adapter.addItem(hourOfDay, minute, (hourOfDay < 12 ? "오전" : "오후"));
                        adapter.notifyDataSetChanged();
                    }
                }, hour, minute, false);
                timePicker.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                timePicker.show();
            }
        });

        ParentLayout.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                hideKeyboard();
                return false;
            }
        });

        long mNow = System.currentTimeMillis();
        Date mReDate = new Date(mNow);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy - MM - dd");
        String formatDate = mFormat.format(mReDate);
        StartDay.setText(formatDate);
        EndDay.setText(formatDate);
        Calendar cal= Calendar.getInstance();
        int sYear = cal.get(Calendar.YEAR);
        int sMonth = cal.get(Calendar.MONTH);
        int sDay = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog_Start = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DecimalFormat formatter = new DecimalFormat("00");
                String formattedMonth = formatter.format(month + 1);
                String formattedDay = formatter.format(dayOfMonth);
                StartDay.setText(year+" - " + formattedMonth + " - " + formattedDay);
            }
        }, sYear, sMonth, sDay);

        DatePickerDialog datePickerDialog_End = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                DecimalFormat formatter = new DecimalFormat("00");
                String formattedMonth = formatter.format(month + 1);
                String formattedDay = formatter.format(dayOfMonth);
                EndDay.setText(year+" - " + formattedMonth + " - " + formattedDay);
            }
        }, sYear, sMonth, sDay);

        openCalStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openCalStartBtn.isClickable()) {
                    datePickerDialog_Start.show();
                }
            }
        });

        openCalEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (openCalEndBtn.isClickable()) {
                    datePickerDialog_End.show();
                }
            }
        });

        everydayBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    monBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    tueBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    wedBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    thuBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    friBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    satBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    sunBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                }
                else {
                    monBtn.getBackground().clearColorFilter();
                    tueBtn.getBackground().clearColorFilter();
                    wedBtn.getBackground().clearColorFilter();
                    thuBtn.getBackground().clearColorFilter();
                    friBtn.getBackground().clearColorFilter();
                    satBtn.getBackground().clearColorFilter();
                    sunBtn.getBackground().clearColorFilter();
                }
            }
        });

        final boolean[] isChecked = {false};

        monBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    monBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    monBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        tueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    tueBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    tueBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        wedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    wedBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    wedBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        thuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    thuBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    thuBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        friBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    friBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    friBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        satBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    satBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    satBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        sunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    sunBtn.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    sunBtn.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });
    }
}