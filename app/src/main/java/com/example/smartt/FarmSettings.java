package com.example.smartt;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.concurrent.ExecutionException;

public class FarmSettings extends AppCompatActivity {
    SwitchCompat aswitchled,aswitchwaterpump, aswitchfan;
    NumberPicker numberPicker1, numberPicker2;
    SeekBar progressBar1, progressBar2;
    String path = "http://211.255.23.65/smallfarmhelper/wemostest/TrArdF.php";
    Button button;
    ImageView button2;
    ConstraintLayout button1;
    String seekbar1value = "20", seekbar2value = "65";
    TextView textView1, textView2;
    int startpick = 0,endpick = 0;
    String switchledvalue,aswtichlwaterpumpvalue,aswitchfanvalue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_farm_settings_upd);
        aswitchled = findViewById(R.id.switch1);
        aswitchwaterpump = findViewById(R.id.switch2);
        numberPicker2 = findViewById(R.id.numberpickerend);
        numberPicker1 = findViewById(R.id.numberpickerstart);
        aswitchfan = findViewById(R.id.switch3);
        numberPicker1.setMinValue(0);
        button1 = findViewById(R.id.buttonback);
        button2 = findViewById(R.id.home);
        numberPicker1.setMaxValue(24);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(24);
        progressBar1 = findViewById(R.id.progress);
        textView1 = findViewById(R.id.temperatureprogress);
        progressBar2 = findViewById(R.id.progress2);
        textView2 = findViewById(R.id.textview_humidity);
        button = findViewById(R.id.buttonid_send);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FarmSettings.this, MainActivity.class);
                startActivity(intent);
            }
        });
        progressBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                  textView1.setText(String.format("%d",i));
                  seekbar1value  = String.format("%d",i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        progressBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView2.setText(String.format("%d",i));
                seekbar2value = String.format("%d",i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                startpick = numberPicker.getValue();
            }
        });
        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                endpick = numberPicker.getValue();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InserttodbFarmValues inserttodbFarmValues = new InserttodbFarmValues();
                inserttodbFarmValues.execute(seekbar1value,seekbar2value,String.format("%d",startpick),String.format("%d",endpick));
            }
        });
        aswitchled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if (isCheked){
                    switchledvalue = "1";
                } else {
                    switchledvalue = "0";
                }
            }
        });
        aswitchwaterpump.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked) {
                if (isCheked){
                    aswtichlwaterpumpvalue = "1";
                } else {
                    aswtichlwaterpumpvalue = "0";
                }
            }
        });
        aswitchfan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isCheked ){
                if (isCheked){
                    aswitchfanvalue = "1";
                } else {
                    aswitchfanvalue = "0";
                }
            }
        });
        AsyncTaskClass asyncTaskClass = new AsyncTaskClass(textView1);
        try {
            String datas = asyncTaskClass.execute(path).get();
            JSONArray jsonArray = new JSONArray(datas);
            switchledvalue = String.valueOf(jsonArray.get(4));
            aswtichlwaterpumpvalue = String.valueOf(jsonArray.get(5));
            aswitchfanvalue = String.valueOf(jsonArray.get(6));
            if (switchledvalue.equals("1")){
                aswitchled.setChecked(true);
            } else {
                aswitchled.setChecked(false);
            }
            if (aswtichlwaterpumpvalue.equals("1")){
                aswitchwaterpump.setChecked(true);
            } else {
                aswitchwaterpump.setChecked(false);
            }
            if (aswitchfanvalue.equals("1")){
                aswitchfan.setChecked(true);
            } else {
                aswitchfan.setChecked(false);
            }

            Log.i("sdas",String.valueOf(jsonArray));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void changbutton(View view) {
        InserttodbFarmSettings inserttodbfan = new InserttodbFarmSettings();
        inserttodbfan.execute(switchledvalue,aswtichlwaterpumpvalue,aswitchfanvalue);
    }
}