package com.example.smartt;

import android.os.AsyncTask;

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

public class InsertHomeTable extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... strings) {
        String pathc = "http://211.255.23.65/smallfarmhelper/wemostest/AndTroutputH.php";
        String led = strings[0];
        String door = strings[1];
        try {
            URL url = new URL(pathc);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            String daat = URLEncoder.encode("Led","UTF-8") + "=" + URLEncoder.encode(led,"UTF-8") + "&" +
                    URLEncoder.encode("Door","UTF-8") + "=" + URLEncoder.encode(door,"UTF-8");
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
