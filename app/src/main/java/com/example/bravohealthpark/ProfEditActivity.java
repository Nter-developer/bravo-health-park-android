package com.example.bravohealthpark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProfEditActivity extends AppCompatActivity {

    private Button profEditEnd_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_edit);

        profEditEnd_Btn = (Button) findViewById(R.id.ProfEditEnd_Btn);

        profEditEnd_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfEditActivity.this, MainActivity.class);
                intent.putExtra("fragment", "setting");
                startActivity(intent);
                finish();
            }
        });
    }
}