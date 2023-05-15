package com.example.bravohealthpark;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView = findViewById(R.id.BottomNavi);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.MainFrame, new HomeFragment()).commit();

        BottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Home_Fragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new HomeFragment()).commit();
                        break;
                }
                return false;
            }
        });
    }
}