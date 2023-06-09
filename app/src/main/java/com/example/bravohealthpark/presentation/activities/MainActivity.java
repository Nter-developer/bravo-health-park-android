package com.example.bravohealthpark.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.infra.preferences.SharedPreferenceBase;
import com.example.bravohealthpark.presentation.fragments.HomeFragment;
import com.example.bravohealthpark.presentation.fragments.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView BottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView = findViewById(R.id.BottomNavi);
        SharedPreferenceBase.initialize(MainActivity.this.getApplicationContext());

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
                    case R.id.Camera_Fragment:
                        openCamera();
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
    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(cameraIntent);
        }
    }
}