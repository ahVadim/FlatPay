package com.example.vadim.flatpay;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Vadim on 17.02.2018.
 */

public class SingleData implements Serializable {

    private String mElectricity;
    private String mColdWater;
    private String mHotWater;
    private Calendar mDate;

    public SingleData(String Electricity, String ColdWater, String HotWater, Calendar date) {
        this.mElectricity = Electricity;
        this.mColdWater = ColdWater;
        this.mHotWater = HotWater;
        this.mDate = date;
    }

    public String getElectricity() {
        return mElectricity;
    }

    public String getColdWater() {
        return mColdWater;
    }

    public String getHotWater() {
        return mHotWater;
    }

    public Calendar getmDate() {
        return mDate;
    }

    public void setElectricity(String mElectricity) {
        this.mElectricity = mElectricity;
    }

    public void setColdWater(String mColdWater) {
        this.mColdWater = mColdWater;
    }

    public void setHotWater(String mHotWater) {
        this.mHotWater = mHotWater;
    }

    public void setmDate(Calendar mDate) {
        this.mDate = mDate;
    }
}
