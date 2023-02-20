package com.example.smartt;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SmartHome extends AppCompatActivity {
    ImageView imageViewback;
    Handler handler = new Handler();
    String datas,datashome2;
    int Gasvalue,Vicinity;
    ArrayList<SmartHomeValues> arrayList = new ArrayList<>();
    TextView textViewtemp, textviewhumidity,textViewvaluetemp, textViewvaluehum,Textviewdust,Textviewdusttitle;
    Switch switchchange;
    ConstraintLayout home;
    SwitchCompat switch11,switch22;
    ImageView imageViewdustface;
    String ledvalue, doorvalue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_home);
        imageViewback = findViewById(R.id.home);
        imageViewdustface = findViewById(R.id.dustface);
        home = findViewById(R.id.buttonback);
        textViewtemp = findViewById(R.id.Temperature);
        Textviewdust = findViewById(R.id.dusttext);
        Textviewdusttitle = findViewById(R.id.dusttitle);
        textviewhumidity = findViewById(R.id.Humidity);
        switchchange = findViewById(R.id.switchvalue);
        textViewvaluetemp = findViewById(R.id.texttemperature);
        textViewvaluehum = findViewById(R.id.texthumidity);
        switch11 = findViewById(R.id.switch1);
        switch22 = findViewById(R.id.switch2);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        refresh(1000);
        try {
            AsyncTaskClass asyncTaskClass1 = new AsyncTaskClass(textViewtemp);
            datashome2 = asyncTaskClass1.execute("http://211.255.23.65/smallfarmhelper/wemostest/TrArdH.php").get();
            JSONArray jsonArray = new JSONArray(datashome2);
             ledvalue = String.valueOf(jsonArray.get(0));
             doorvalue = String.valueOf(jsonArray.get(1));
            if (ledvalue.equals("1")){
                switch11.setChecked(true);
            } else {
                switch11.setChecked(false);
            }
         if ( doorvalue.equals("1")){
             switch22.setChecked(true);
         } else {
            switch22.setChecked(false);
           }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        switch11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    ledvalue = "1";
                }  else {
                    ledvalue = "0";
                }
            }
        });
        switch22.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    doorvalue = "1";
                }  else {
                    doorvalue = "0";
                }
            }
        });
    }

    public void refresh(int miliseconds){
       final Runnable runnable  = new Runnable() {
            @Override
            public void run() {
                try {
                    content();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable,miliseconds);
    }

    public void content() throws ExecutionException, InterruptedException, JSONException {
        AsyncTaskClass asyncTaskClass = new AsyncTaskClass(textViewtemp);
        datas = asyncTaskClass.execute("http://211.255.23.65/smallfarmhelper/apihome/TrAndH.php").get();
        switchchange.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    textViewtemp.setVisibility(View.INVISIBLE);
                    textviewhumidity.setVisibility(View.VISIBLE);
                    textViewvaluetemp.setVisibility(View.INVISIBLE);
                    textViewvaluehum.setVisibility(View.VISIBLE);
                } else {
                    textViewtemp.setVisibility(View.VISIBLE);
                    textviewhumidity.setVisibility(View.INVISIBLE);
                    textViewvaluetemp.setVisibility(View.VISIBLE);
                    textViewvaluehum.setVisibility(View.INVISIBLE);
                }
            }
        });
        imageViewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        JsonUnits jsonUnits = new JsonUnits();
        arrayList = jsonUnits.getdatas3(datas);

        for (int i = 0; i < arrayList.size();i++) {
            textViewtemp.setText(arrayList.get(i).getTemperature() + "℃");
            textviewhumidity.setText(arrayList.get(i).getHumidity() + "%");
            Textviewdusttitle.setText("미세먼지 : " + arrayList.get(i).getFinedust() + "Mg");
            double dust = (double) Double.parseDouble(arrayList.get(i).getFinedust());
            if (dust <= 30.0){
                Textviewdust.setText("좋음");
                imageViewdustface.setImageResource(R.drawable.happiness);
            } else  if (30.0 < dust && dust < 80.0){
                Textviewdust.setText("보통");
                imageViewdustface.setImageResource(R.drawable.emoticon);
            } else if (dust >= 80.0){
                Textviewdust.setText("나쁨");
                imageViewdustface.setImageResource(R.drawable.sad);
            }
            if (arrayList.get(i).getGasvalue() != null) {
                Gasvalue = Integer.parseInt(arrayList.get(i).getGasvalue());
                 Vicinity = Integer.parseInt(arrayList.get(i).getVicinity());

            }
        }
        if (Gasvalue == 0) {
            Toast.makeText(this, "가스 세고 있습니다", Toast.LENGTH_SHORT).show();
        }
        if (Vicinity == 0) {
            Toast.makeText(this, "감지 발생하고 있습니다", Toast.LENGTH_SHORT).show();
        }
        refresh(1000);
    }


    public void onclickchangebutton(View view) {
        InsertHomeTable insertHomeTable = new InsertHomeTable();
        insertHomeTable.execute(ledvalue,doorvalue);
    }
}