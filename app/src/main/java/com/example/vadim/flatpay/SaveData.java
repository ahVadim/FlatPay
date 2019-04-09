package com.example.vadim.flatpay;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.ContactsContract;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vadim on 17.02.2018.
 */

public class SaveData {
    public static final String SHARED_PREF_NAME = "SAVED DATA";
    public static final String DATA_KEY = "SAVED DATA KEY";
    public static final String RATES_KEY = "RATES KEY";

    private SharedPreferences mSharedPref;
    private Gson mGson = new Gson();
    public static final Type SAVED_DATA_TYPE = new TypeToken<List<SingleData>>(){}.getType();
    public static final Type SAVED_RATES_TYPE = new TypeToken<Rates>(){}.getType();

    public SaveData(Context context){
        this.mSharedPref = context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
    }


    public List<SingleData> getData() {
        List<SingleData> allData = mGson.fromJson(mSharedPref.getString(DATA_KEY,null),SAVED_DATA_TYPE);
        return allData == null ? new ArrayList<SingleData>() : allData;
    }

    public SingleData getLastData (){
        List<SingleData> allData = getData();
        if (allData.size()>0){
            return getData().get(allData.size()-1);
        }
        else return null;
    }

    public boolean DeleteLastData (){
        List<SingleData> allData = getData();
        if (allData != null && allData.size() > 0){
        allData.remove(allData.size()-1);
        mSharedPref.edit().putString(DATA_KEY, mGson.toJson(allData,SAVED_DATA_TYPE)).apply();
        return true;}
        else return false;
    }

    /*public String getJsonData() {
        return  mSharedPref.getString(DATA_KEY,null);
    }*/

    public void addData(SingleData mData){
        List<SingleData> allData = getData();
        allData.add(mData);
        mSharedPref.edit().putString(DATA_KEY, mGson.toJson(allData,SAVED_DATA_TYPE)).apply();
    }

    public void saveRates(Rates mRates){
        mSharedPref.edit().putString(RATES_KEY, mGson.toJson(mRates, SAVED_RATES_TYPE)).apply();
    }

    public Rates getRates (){
        return mGson.fromJson(mSharedPref.getString(RATES_KEY,null),SAVED_RATES_TYPE);
    }

    public float calcSum (){
        List<SingleData> allData = getData();
        if (allData.size() < 2) {
            return 0;
        } else {
            SingleData data1 = allData.get(allData.size() - 2);
            SingleData data2 = allData.get(allData.size() - 1);
            float el1 = Float.parseFloat(data1.getElectricity());
            float cw1 = Float.parseFloat(data1.getColdWater());
            float hw1 = Float.parseFloat(data1.getHotWater());
            float el2 = Float.parseFloat(data2.getElectricity());
            float cw2 = Float.parseFloat(data2.getColdWater());
            float hw2 = Float.parseFloat(data2.getHotWater());

            Rates mRates = getRates();
            if (mRates == null) return 0;
            float rEl = mRates.getElectricityRate();
            float rCW = mRates.getColdWaterRate();
            float rHW = mRates.getHotWaterRate();
            return  (el2-el1)*rEl + (cw2-cw1)*rCW + (hw2-hw1)*rHW;
        }




    }
}
