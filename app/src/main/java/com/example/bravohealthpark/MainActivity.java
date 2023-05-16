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
                        return true;
                    case R.id.Setting_Fragment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.MainFrame,new SettingFragment()).commit();
                        return true;
                }
                return false;
            }
        });

        if (getIntent().hasExtra("fragment")) {
            String fragment = getIntent().getStringExtra("fragment");
            if (fragment.equals("setting")) {
                BottomNavigationView.setSelectedItemId(R.id.Setting_Fragment);
            }
        }
    }
}