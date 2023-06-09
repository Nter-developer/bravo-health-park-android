package com.example.bravohealthpark.presentation.activities;

import static com.example.bravohealthpark.infra.utils.IntentUtils.startNewActivity;
import static com.example.bravohealthpark.infra.utils.ToastUtils.showToast;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.domain.medication.domain.dto.SaveMediInfoRequest;
import com.example.bravohealthpark.domain.medication.domain.dto.SaveMediInfoResponse;
import com.example.bravohealthpark.domain.medication.services.MedicationInfoService;
import com.example.bravohealthpark.global.error.ErrorMessages;
import com.example.bravohealthpark.infra.preferences.SharedPreferenceBase;
import com.example.bravohealthpark.infra.preferences.UserPreferences;
import com.example.bravohealthpark.infra.retrofit.RetrofitClient;
import com.example.bravohealthpark.infra.utils.Messages;

import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedRegistrationActivity extends AppCompatActivity {

    private static MedicationInfoService medicationInfoService;
    Call<SaveMediInfoResponse> call;
    private static SaveMediInfoRequest saveMediInfoRequest;
    private EditText editTextMediName, editTextDoseDays;
    private CheckBox checkBoxAfterMeal, checkBoxBeforeMeal, checkBoxImmeAfterMeal;
    private CheckBox checkBoxMorning, checkBoxLunch, checkBoxDinner, checkBoxNight;
    private Button buttonMediRegister;

    void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_registration);
        // initialize components
        initComponents();

        buttonMediRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateMediInfoRegisterForm();

                createSaveMediInfoRequestDto();
                medicationInfoService = RetrofitClient.getApiService(MedicationInfoService.class);
                String loginId = SharedPreferenceBase.getSharedPreference(UserPreferences.PREFERENCE_USER_LOGIN_ID);
                call = medicationInfoService.saveMedicationInfo(loginId, saveMediInfoRequest);

                call.enqueue(new Callback<SaveMediInfoResponse>() {
                    @Override
                    public void onResponse(Call<SaveMediInfoResponse> call, Response<SaveMediInfoResponse> response) {
                        if(response.isSuccessful()) {
                            showToast(getApplicationContext(), Messages.MEDI_REGISTER_SUCCESS);
                            startNewActivity(getApplicationContext(), MainActivity.class, true);
                        }
                        else {
                            showToast(getApplicationContext(), ErrorMessages.MEDI_REGISTER_FAIL);
                        }
                    }

                    @Override
                    public void onFailure(Call<SaveMediInfoResponse> call, Throwable t) {
                        showToast(getApplicationContext(), ErrorMessages.NETWORK_ERROR);
                    }
                });
            }
        });
    }

    private boolean validateMediInfoRegisterForm() {
        Optional<String> doseDays = Optional.ofNullable(editTextMediName.getText().toString());
        if(doseDays.isPresent()) {
            try {
                int number = Integer.parseInt(doseDays.get().toString());
                // 정상적으로 문자열을 정수로 변환한 경우에 대한 처리
            } catch (NumberFormatException e) {
                // 문자열이 올바른 형식이 아닌 경우에 대한 처리
                // 예: 오류 메시지 출력, 기본값 설정 등
            }
        }
        boolean valid1 = Optional.ofNullable(editTextMediName.getText().toString()).isPresent();
        boolean valid2 = Optional.ofNullable(editTextDoseDays.getText().toString()).isPresent();
        return valid1&&valid2;
    }

    private void createSaveMediInfoRequestDto() {
        int dailyDose = 0;
        if(checkBoxMorning.isChecked()) dailyDose++;
        if(checkBoxLunch.isChecked()) dailyDose++;
        if(checkBoxDinner.isChecked()) dailyDose++;
        if(checkBoxNight.isChecked()) dailyDose++;
        String str = Optional.ofNullable(editTextDoseDays.getText().toString()).orElse("");
        int doseDays = Integer.parseInt(str);
        saveMediInfoRequest = new SaveMediInfoRequest(
                doseDays, editTextMediName.getText().toString(),
                0, dailyDose);
    }

    private void initComponents() {
        editTextMediName = (EditText) findViewById(R.id.edittext_input_medicine_name);
        editTextDoseDays = (EditText) findViewById(R.id.edittext_input_dose_days);
        checkBoxAfterMeal = (CheckBox) findViewById(R.id.checkbox_dose_30m_after_meal);
        checkBoxBeforeMeal = (CheckBox) findViewById(R.id.checkbox_dose_30m_before_meal);
        checkBoxImmeAfterMeal = (CheckBox) findViewById(R.id.checkbox_dose_immediately_after_eating);
        checkBoxMorning = (CheckBox) findViewById(R.id.checkbox_dose_morning);
        checkBoxLunch = (CheckBox) findViewById(R.id.checkbox_dose_lunch);
        checkBoxDinner = (CheckBox) findViewById(R.id.checkbox_dose_dinner);
        checkBoxNight = (CheckBox) findViewById(R.id.checkbox_dose_night);
        buttonMediRegister = (Button) findViewById(R.id.button_medi_register);
    }
}