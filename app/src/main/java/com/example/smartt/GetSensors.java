package com.example.smartt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class GetSensors extends AppCompatActivity {
    String url = "http://211.255.23.65/smallfarmhelper/apibarn/TrAndB.php",datas, gas,temperature, humidity, temperature_text;
    ArrayList<SensorsValue> arrayList;
    int value0fdistanceprogress,percentege,valueofwater, valueofdistance;
    ProgressBar progressBar, progressBar0;
    AsyncTaskClass asyncTaskClass;
    Button  buttongo;
    ImageView home;
    ConstraintLayout buttonback;
    TextView textView, textView1, textView2, textView3, textviewhum, textView4, textView5;
    Handler handler = new Handler();
    double temperature_fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barn);
       refresh(1000);

        textView = findViewById(R.id.textview_temp);
        progressBar = findViewById(R.id.progress_bar1);
        textView4 = findViewById(R.id.temperatureprogress);
        textView5 = findViewById(R.id.humidityprogress);
        buttongo = findViewById(R.id.buttongo);
        buttonback = findViewById(R.id.buttonback);
        progressBar0 = findViewById(R.id.progress_bar0);
        textView2 = findViewById(R.id.valueofwater);
        textView3 = findViewById(R.id.valueofdistance);
        textviewhum = findViewById(R.id.textview_humidity);
        home = findViewById(R.id.home);


        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttongo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GetSensors.this, BarnSettings.class);
                startActivity(intent);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GetSensors.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void content() {
        asyncTaskClass = new AsyncTaskClass(textView);
        try {
            datas = asyncTaskClass.execute(url).get();
            JsonUnits jsonUnits = new JsonUnits();
            arrayList = jsonUnits.getdatas(datas);
            for (int i = 0; i < arrayList.size(); i++) {
                temperature = arrayList.get(i).getTemperature();
                humidity = arrayList.get(i).getHumidity();
                valueofwater = Integer.parseInt(arrayList.get(i).getWater());
                valueofdistance = Integer.parseInt(arrayList.get(i).getDistance());
                if (valueofdistance < 4){
                    value0fdistanceprogress = 800;
                } else if (valueofdistance < 9 && valueofdistance >= 4){
                    value0fdistanceprogress = 500;
                } else if (valueofdistance >= 10){
                    value0fdistanceprogress = 100;
                }
                progressBar.setProgress(valueofwater);
                progressBar0.setProgress(value0fdistanceprogress);
                percentege = valueofwater / 10;
                if (0 <= valueofwater && valueofwater <= 400) {
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbarred));
                } else if (400 < valueofwater && valueofwater < 800) {
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbaryellow));
                } else {
                    progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.progressbargreen));
                }
              switch (value0fdistanceprogress){
                  case 800:
                      progressBar0.setProgressDrawable(getResources().getDrawable(R.drawable.progressbargreen));
                      textView3.setText("충분함");
                      break;
                  case 500:
                      progressBar0.setProgressDrawable(getResources().getDrawable(R.drawable.progressbaryellow));
                      textView3.setText("보통");
                      break;
                  case 100:
                      progressBar0.setProgressDrawable(getResources().getDrawable(R.drawable.progressbarred));
                      textView3.setText("부족함");
                  default:
                      break;
              }
                textView2.setText(percentege + "%");
                textView.setText("온도 : " + temperature + "℃");
                textviewhum.setText("습도 : " + humidity + "%");
                temperature_fl = Double.parseDouble(temperature);

                 gas = arrayList.get(i).getCo2gas();

            }
//            if (percentege < 60) {
//                Toast.makeText(this, "물 공급 해 주세요", Toast.LENGTH_SHORT).show();
//            }
            if (gas != "") {
                if (gas == "0") {
                    Toast.makeText(this, "가스 세고 있습니다", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        refresh(1000);
    }

    public void refresh(int miliseconds) {
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                content();
            }
        };
        handler.postDelayed(runnable, miliseconds);
    }
}