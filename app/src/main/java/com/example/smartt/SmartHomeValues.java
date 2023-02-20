package com.example.smartt;

public class SmartHomeValues {
    String temperature;
    String humidity;
    String vicinity;
    String gasvalue;
    String finedust;

    public SmartHomeValues(String temperature, String humidity, String vicinity, String gasvalue, String finedust) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.vicinity = vicinity;
        this.gasvalue = gasvalue;
        this.finedust = finedust;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public String getGasvalue() {
        return gasvalue;
    }

    public void setGasvalue(String gasvalue) {
        this.gasvalue = gasvalue;
    }

    public String getFinedust() {
        return finedust;
    }

    public void setFinedust(String finedust) {
        this.finedust = finedust;
    }
}
