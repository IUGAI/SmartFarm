package com.example.smartt;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class InserttoDb extends AsyncTask<String, Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        String path = "http://211.255.23.65/smallfarmhelper/wemostest/AndTrinputB.php";
        String temperature, humidity, food;
        temperature = strings[0];
        humidity = strings[1];
        food = strings[2];


        try {
            URL url = new URL(path);
            HttpURLConnection httpURLConnection =  (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream OS = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));
            String daat = URLEncoder.encode("Temperature","UTF-8") + "=" + URLEncoder.encode(temperature,"UTF-8") + "&" +
                    URLEncoder.encode("Humidity","UTF-8") + "=" + URLEncoder.encode(humidity,"UTF-8") + "&" +
                    URLEncoder.encode("Food","UTF-8") + "=" + URLEncoder.encode(food,"UTF-8");
            bufferedWriter.write(daat);
            bufferedWriter.flush();
            bufferedWriter.close();
            InputStream is = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"));
            String reulst = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                reulst += line;
            }
            bufferedReader.close();
            is.close();
            httpURLConnection.disconnect();
            return  reulst;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
