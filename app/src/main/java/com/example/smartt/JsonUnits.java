package com.example.smartt;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUnits {

   public  ArrayList<SensorsValue> getdatas(String json) throws JSONException {
       ArrayList<SensorsValue> arrayList = new ArrayList<>();
       ArrayList<SmartFarmValues> arrayList2 = new ArrayList<>();
       String temperature1 = null;
       String humidity1 = null;
       String water1 = null;
       String distance1 = null;
       String Co2gas = "";
       try {
           JSONArray jsonArray = new JSONArray(json);
           for (int i = 0; i < jsonArray.length();i++){
               JSONObject jsonObject = jsonArray.getJSONObject(i);
               float temperature = (float) jsonObject.getDouble("Temprature");
               float humidity = (float) jsonObject.getDouble("Humidity");
               int water = jsonObject.getInt("Water");
               int distanse = jsonObject.getInt("Distance");
               if (jsonObject.getString("Co2Gas") != null) {
                    Co2gas = jsonObject.getString("Co2Gas");
               }
               temperature1  = String.format("%.1f", temperature);
               humidity1 = String.format("%.1f ", humidity);
               water1 = String.format("%d", water);
               distance1 = String.format("%d", distanse);
               arrayList.add(new SensorsValue(temperature1,humidity1,water1,distance1,Co2gas));//
           }
       } catch (JSONException e) {
           e.printStackTrace();
       }
       return  arrayList;
   }

    public  ArrayList<SmartFarmValues> getdatas2(String json) throws JSONException {
        ArrayList<SensorsValue> arrayList = new ArrayList<>();
        ArrayList<SmartFarmValues> arrayList2 = new ArrayList<>();
        String temperaturefarm = null;
        String humidityfarm = null;
        String water1 = null;
        String distance1 = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                float temperature = (float) jsonObject.getDouble("Temprature");
                float humidity = (float) jsonObject.getDouble("Humidity");
                int water = jsonObject.getInt("Waterlevel");
                int soillevel = jsonObject.getInt("Soillevel");
                int co2gas = jsonObject.getInt("Co2Gas");
                String soillelvel = String.format("%d",soillevel);
                String co2gasstring = String.format("%d",co2gas);
                temperaturefarm = String.format("%.1f", temperature);
                humidityfarm = String.format("%.1f ", humidity);
                water1 = String.format("%d", water);
                arrayList2.add(new SmartFarmValues(temperaturefarm,humidityfarm,water1,soillelvel,co2gasstring));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  arrayList2;
    }

    public  ArrayList<SmartHomeValues> getdatas3(String json) throws JSONException {
        ArrayList<SmartHomeValues> arrayList2 = new ArrayList<>();
        String temperaturefarm = null;
        String humidityfarm = null;
        String Vicinity = null;
        String Gasvalue = null;
        String finedust = null;
        try {
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                float temperature = (float) jsonObject.getDouble("Temprature");
                float humidity = (float) jsonObject.getDouble("Humidity");
                if (jsonObject.getString("Vicinity") != null) {
                     Vicinity = jsonObject.getString("Vicinity");
                }
                if (jsonObject.getString("Gasvalue") != null) {
                     Gasvalue = jsonObject.getString("Gasvalue");
                }
                int Gasvalue1 = jsonObject.getInt("Gasvalue");
                float Finedust = (float)jsonObject.getDouble("finedust");
                temperaturefarm = String.format("%.1f", temperature);
                humidityfarm = String.format("%.1f ", humidity);
                finedust = String.format("%.1f",Finedust);
                arrayList2.add(new SmartHomeValues(temperaturefarm,humidityfarm,Vicinity,Gasvalue,finedust));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  arrayList2;
    }

}
