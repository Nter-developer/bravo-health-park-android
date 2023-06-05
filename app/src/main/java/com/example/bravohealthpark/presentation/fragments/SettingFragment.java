package com.example.bravohealthpark.presentation.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.bravohealthpark.R;
import com.example.bravohealthpark.presentation.activities.PrefSoundActivity;
import com.example.bravohealthpark.presentation.activities.ProfEditActivity;

public class SettingFragment extends Fragment {

    private Button profEdit_Btn;
    private Button soundSetting_Btn;
    private Button callService_Btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_setting, container, false);

        profEdit_Btn = v.findViewById(R.id.ProfEdit_Btn);
        soundSetting_Btn = v.findViewById(R.id.GoSoundSetting_Btn);
        callService_Btn = v.findViewById(R.id.Call_Service_Btn);
        profEdit_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfEditActivity.class);
                startActivity(intent);
            }
        });
        soundSetting_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrefSoundActivity.class);
                startActivity(intent);
            }
        });
        callService_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:01012345678"));
                //Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:01012345678"));
                startActivity(intent);
            }
        });
        return v;
    }
}