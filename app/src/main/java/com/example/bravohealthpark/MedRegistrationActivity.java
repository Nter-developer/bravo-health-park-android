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
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MedRegistrationActivity extends AppCompatActivity {

    private EditText Med_Name;

    private LinearLayout ParentLayout;

    private EditText StartDay;
    private EditText EndDay;
    private Button OpenCal_Start;
    private Button OpenCal_End;

    private CheckBox Check_Everyday;
    private Button Check_Mon;
    private Button Check_Tue;
    private Button Check_Wed;
    private Button Check_Thu;
    private Button Check_Fri;
    private Button Check_Sat;
    private Button Check_Sun;

    private Button AddTime_Btn;
    private ListView Alarm_ListView;

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

        Med_Name = findViewById(R.id.Med_Name);

        ParentLayout = findViewById(R.id.ParentLayout);

        StartDay = findViewById(R.id.StartDay);
        EndDay = findViewById(R.id.EndDay);
        OpenCal_Start = findViewById(R.id.OpenCal_Start);
        OpenCal_End = findViewById(R.id.OpenCal_End);

        Check_Everyday = findViewById(R.id.Check_Everyday);
        Check_Mon = findViewById(R.id.Check_Mon);
        Check_Tue = findViewById(R.id.Check_Tue);
        Check_Wed = findViewById(R.id.Check_Wed);
        Check_Thu = findViewById(R.id.Check_Thu);
        Check_Fri = findViewById(R.id.Check_Fri);
        Check_Sat = findViewById(R.id.Check_Sat);
        Check_Sun = findViewById(R.id.Check_Sun);

        AddTime_Btn = findViewById(R.id.AddTime_Btn);
        Alarm_ListView = findViewById(R.id.Alarm_ListView);


        Alarm_ListView = (ListView) findViewById(R.id.Alarm_ListView);
        adapter = new AdapterActivity();
        Alarm_ListView.setAdapter(adapter);

        Alarm_ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        AddTime_Btn.setOnClickListener(new View.OnClickListener() {
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

        OpenCal_Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OpenCal_Start.isClickable()) {
                    datePickerDialog_Start.show();
                }
            }
        });

        OpenCal_End.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (OpenCal_End.isClickable()) {
                    datePickerDialog_End.show();
                }
            }
        });



        Check_Everyday.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Check_Mon.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Tue.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Wed.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Thu.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Fri.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Sat.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    Check_Sun.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                }
                else {
                    Check_Mon.getBackground().clearColorFilter();
                    Check_Tue.getBackground().clearColorFilter();
                    Check_Wed.getBackground().clearColorFilter();
                    Check_Thu.getBackground().clearColorFilter();
                    Check_Fri.getBackground().clearColorFilter();
                    Check_Sat.getBackground().clearColorFilter();
                    Check_Sun.getBackground().clearColorFilter();
                }
            }
        });

        final boolean[] isChecked = {false};

        Check_Mon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Mon.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Mon.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Tue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Tue.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Tue.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Wed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Wed.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Wed.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Thu.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Thu.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Fri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Fri.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Fri.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Sat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Sat.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Sat.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });

        Check_Sun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isChecked[0]){
                    Check_Sun.getBackground().setColorFilter(getResources().getColor(R.color.button_checked), PorterDuff.Mode.SRC_IN);
                    isChecked[0] = true;

                }
                else{
                    Check_Sun.getBackground().clearColorFilter();
                    isChecked[0] = false;
                }
            }
        });




    }



}