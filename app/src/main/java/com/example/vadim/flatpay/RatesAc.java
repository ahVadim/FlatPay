package com.example.vadim.flatpay;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class RatesAc extends AppCompatActivity {



    EditText mElectricity;
    EditText mColdWater;
    EditText mHotWater;
    Button mSaveBtn;
    SaveData mSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rates);

        mSaveData = new SaveData(this);

        mElectricity = (EditText) findViewById(R.id.elRateTV);
        mColdWater = (EditText) findViewById(R.id.cwRateTV);
        mHotWater = (EditText) findViewById(R.id.hwRateTV);
        mSaveBtn = (Button) findViewById(R.id.rates_save_button);
        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveRates();
            }
        });


    }

    private void saveRates(){
        if (!isRatesCorrect()) {
            Toast.makeText(this, "Введите данные",Toast.LENGTH_SHORT).show();
            return;
        }
        Rates savedRates = new Rates();
        savedRates.setElectricityRate(Float.parseFloat(mElectricity.getText().toString()));
        savedRates.setColdWaterRate(Float.parseFloat(mColdWater.getText().toString()));
        savedRates.setHotWaterRate(Float.parseFloat(mHotWater.getText().toString()));
        mSaveData.saveRates(savedRates);
        Toast.makeText(this, "Успешно сохранено",Toast.LENGTH_SHORT).show();
        setResult(RESULT_OK);
        finish();

    }

    private boolean isRatesCorrect (){
        return !(mElectricity.getText().toString().isEmpty() || mColdWater.getText().toString().isEmpty() || mHotWater.getText().toString().isEmpty());
    }
}
