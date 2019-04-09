package com.example.vadim.flatpay;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class AddDataAc extends AppCompatActivity {

    public static String INTENT_KEY = "INTENT KEY";

    private EditText mElectricityView;
    private EditText mColdWaterView;
    private EditText mHotWaterView;
    private EditText mDay;
    private EditText mMonth;
    private EditText mYear;
    private Button mSaveDataBtn;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        mElectricityView = (EditText) findViewById(R.id.electricity);
        mColdWaterView = (EditText) findViewById(R.id.coldWater);
        mHotWaterView = (EditText) findViewById(R.id.hotWater);
        mDay = (EditText) findViewById(R.id.day);
        mMonth = (EditText) findViewById(R.id.month);
        mYear = (EditText) findViewById(R.id.year);
        mSaveDataBtn = (Button) findViewById(R.id.data_save_button);

        calendar = Calendar.getInstance();
        mDay.setText(Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)));
        mMonth.setText(Integer.toString(calendar.get(Calendar.MONTH)+1));
        mYear.setText(Integer.toString(calendar.get(Calendar.YEAR)));



        mSaveDataBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean actionSaveFlag = actionSave();
                if(actionSaveFlag) {
                    /*Intent intent = new Intent();
                    intent.putExtra(INTENT_KEY, mData);*/
                    setResult(RESULT_OK);
                    ShowMessage("Успешно сохранено");
                    finish();
                } else ShowMessage("Некорректный ввод данных");
            }
        });


    }

    private boolean actionSave() {
            String el = mElectricityView.getText().toString();
            String cw = mColdWaterView.getText().toString();
            String hw = mHotWaterView.getText().toString();
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(mDay.getText().toString()));
            calendar.set(Calendar.MONTH, Integer.parseInt(mMonth.getText().toString())-1);
            calendar.set(Calendar.YEAR, Integer.parseInt(mYear.getText().toString()));
            if (!el.isEmpty()&& !cw.isEmpty() && !hw.isEmpty()) {
            SingleData mData = new SingleData(el, cw, hw, calendar);
            SaveData mSaveData = new SaveData(this);
            mSaveData.addData(mData);
            return true;
        } else return false;
    }

    private void ShowMessage (String text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}

