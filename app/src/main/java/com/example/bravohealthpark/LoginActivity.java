package com.example.bravohealthpark;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bravohealthpark.retrofit.LoginDto;
import com.example.bravohealthpark.retrofit.LoginResponse;
import com.example.bravohealthpark.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private RetrofitService retrofitService;
    private Button loginBtn, singUpBtn;
    private EditText loginId, phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginBtn = (Button) findViewById(R.id.Login_Btn);
        singUpBtn = (Button) findViewById(R.id.Signup_Btn);
        loginId = (EditText) findViewById(R.id.edittext_login_id);
        phoneNumber = (EditText) findViewById(R.id.edittext_login_pnum);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofitService = RetrofitClient.getApiService();
                LoginDto loginDto = new LoginDto(loginId.getText().toString(), phoneNumber.getText().toString());
                Call<LoginResponse> call = retrofitService.sendLoginRequest(loginDto);
                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()) {
                            Intent intent = new Intent(getApplicationContext(), SplashActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(LoginActivity.this.getApplicationContext(),"아이디와 비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                    }
                });
            }
        });

        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
