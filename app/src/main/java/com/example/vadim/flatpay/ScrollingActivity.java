package com.example.vadim.flatpay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ScrollingActivity extends AppCompatActivity {

    TextView mSumText;
    SaveData mSaveData;
    TableLayout mTable;
    CollapsingToolbarLayout mCollapse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);


        mSumText = (TextView) findViewById(R.id.sumText);
        mSaveData = new SaveData(this);
        mTable = (TableLayout) findViewById(R.id.tableLayout);
        FillTable(mTable,mSaveData);
        //mDataText = (TextView) findViewById(R.id.dataTV);
        //mDataText.setText(mSaveData.getJsonData());



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 2) {
            if (resultCode == RESULT_OK) showSum();
        }

        if (requestCode==1) {

            if (resultCode == RESULT_OK) {
                SingleData mData = mSaveData.getLastData();
                AddRow(mTable, mData);
                showSum();
            }
        }


    }

    public void showMessage (String text){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }

    public void FillTable (TableLayout mTable, SaveData mSaveData){
        List<SingleData> allData = mSaveData.getData();
        for (SingleData d:allData){
            AddRow(mTable,d);
        }
        showSum();
    }

    public void showSum () {
            mSumText.setText(mSaveData.calcSum() + getString(R.string.rubles));

    }



    public void AddRow (TableLayout mTable, SingleData mData){
        TableRow dataRow = new TableRow(this);



        TextView dateText = new TextView(this);
        String day = Integer.toString(mData.getmDate().get(Calendar.DAY_OF_MONTH));
        String month = mData.getmDate().getDisplayName(Calendar.MONTH,Calendar.SHORT,Locale.ROOT);
        String year = Integer.toString(mData.getmDate().get(Calendar.YEAR)-2000);
        dateText.setText(day+"/"+month+"/"+year);
        TextView elText = new TextView(this);
        elText.setGravity(Gravity.CENTER);
        elText.setText(mData.getElectricity());
        TextView cwText = new TextView(this);
        cwText.setGravity(Gravity.CENTER);
        cwText.setText(mData.getColdWater());
        TextView hwText = new TextView(this);
        hwText.setGravity(Gravity.CENTER);
        hwText.setText(mData.getHotWater());

        dataRow.addView(dateText);
        dataRow.addView(elText);
        dataRow.addView(cwText);
        dataRow.addView(hwText);

        mTable.addView(dataRow);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id){
            case (R.id.action_delete): {
            if (mSaveData.DeleteLastData()){
            mTable.removeViewAt(mTable.getChildCount()-1);
            showSum();
            return true;}
            else return false;
            }
            case (R.id.action_add): {
                final Intent addDataIntent = new Intent(this, AddDataAc.class);
                startActivityForResult(addDataIntent,1);
                return true;
            }
            case (R.id.action_rates):{
                Intent ratesIntent = new Intent(this,RatesAc.class);
                startActivityForResult(ratesIntent,2);
                return true;
            }
            case (R.id.action_send_message): {
                Intent sendMessIntent = new Intent(this,ShowMessageAc.class);
                startActivity(sendMessIntent);
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
