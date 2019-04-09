package com.example.vadim.flatpay;

/**
 * Created by Vadim on 18.02.2018.
 */

public class Rates {

    private float electricityRate;
    private float coldWaterRate;
    private float hotWaterRate;

    public float getElectricityRate() {
        return electricityRate;
    }

    public float getColdWaterRate() {
        return coldWaterRate;
    }

    public float getHotWaterRate() {
        return hotWaterRate;
    }

    public void setElectricityRate(float electricityRate) {
        this.electricityRate = electricityRate;
    }

    public void setColdWaterRate(float coldWaterRate) {
        this.coldWaterRate = coldWaterRate;
    }

    public void setHotWaterRate(float hotWaterRate) {
        this.hotWaterRate = hotWaterRate;
    }
}
