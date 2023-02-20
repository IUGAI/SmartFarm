package com.example.smartt;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class AsyncTaskClass extends AsyncTask<String, Void,String> {

    TextView textView;

    public AsyncTaskClass(TextView textView) {
   this.textView = textView;
    }

    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        InputStream inputStream = null;
        try {
            URL url = new URL(strings[0]);
            inputStream  = url.openStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line + "\n");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  builder.toString();
    }

}
