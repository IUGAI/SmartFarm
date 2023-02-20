package com.example.smartt;

public class SmartFarmValues {
    private  String Temperature;
    private  String Humidity;
    private  String Water;
    private String Co2Gas;
    private  String Solid;

    public SmartFarmValues(String temperature, String humidity, String water, String solid,String co2gas) {
        Temperature = temperature;
        Humidity = humidity;
        Water = water;
        Solid = solid;
        Co2Gas = co2gas;
    }

    public String getCo2Gas() {
        return Co2Gas;
    }

    public void setCo2Gas(String co2Gas) {
        Co2Gas = co2Gas;
    }

    public String getTemperature() {
        return Temperature;
    }

    public void setTemperature(String temperature) {
        Temperature = temperature;
    }

    public String getHumidity() {
        return Humidity;
    }

    public void setHumidity(String humidity) {
        Humidity = humidity;
    }

    public String getWater() {
        return Water;
    }

    public void setWater(String water) {
        Water = water;
    }

    public String getSolid() {
        return Solid;
    }

    public void setSolid(String solid) {
        Solid = solid;
    }
}
