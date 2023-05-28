package com.example.bravohealthpark;

import static com.example.bravohealthpark.retrofit.RetrofitClient.getApiService;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private Button signUpBtn;
    private EditText editTextName, editTextPNumber, editTextId;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpBtn = (Button) findViewById(R.id.SignUp_Btn);
        editTextId = (EditText) findViewById(R.id.EditText_Id);
        editTextName = (EditText) findViewById(R.id.EditText_Name);
        editTextPNumber = (EditText) findViewById(R.id.EditText_PhoneNumber);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofitService = getApiService();

                HashSet<AuthorityDto> authorityDtoSet = new HashSet<>();
                authorityDtoSet.add(new AuthorityDto("ROLE_USER"));
                UserDto requestData = new UserDto(authorityDtoSet,
                        editTextId.getText().toString(),
                        editTextPNumber.getText().toString(),
                        editTextName.getText().toString());
                Call<SignupResult> call = retrofitService.sendSignupRequest(requestData);
                call.enqueue(new Callback<SignupResult>() {
                    @Override
                    public void onResponse(Call<SignupResult> call, Response<SignupResult> response) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<SignupResult> call, Throwable t) {
                        if (t instanceof IOException) {
                            // 네트워크 연결 문제로 통신 실패한 경우
                            // 예: 인터넷 연결 없음, 서버에 연결할 수 없음 등
                            System.out.println("Network connection failed: " + t.getMessage());
                        } else if (t instanceof HttpException) {
                            // 서버로부터 응답을 받지 못한 경우 (HTTP 응답 코드가 2xx가 아닌 경우)
                            // 예: 404 Not Found, 500 Internal Server Error 등
                            HttpException httpException = (HttpException) t;
                            int statusCode = httpException.code();
                            String errorMessage = httpException.message();
                            System.out.println("Server error - Status code: " + statusCode + ", Error message: " + errorMessage);
                        } else {
                            // 기타 예외 상황인 경우
                            System.out.println("Error occurred: " + t.getMessage());
                        }
                    }
                });
            }
        });
    }
}
