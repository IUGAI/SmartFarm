package com.example.smartt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
ConstraintLayout button,buttonFarm,buttonhome;
NavigationView dehaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       button = findViewById(R.id.button1);
       buttonFarm = findViewById(R.id.button3);
       buttonhome = findViewById(R.id.button2);
       dehaze = (NavigationView) findViewById(R.id.dehaze);
       dehaze.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(MainActivity.this, "ne_on", Toast.LENGTH_SHORT).show();
           }
       });
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this, GetSensors.class);
               startActivity(intent);
           }
       });
       buttonFarm.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent1 = new Intent(MainActivity.this, SmartFarm.class);
               startActivity(intent1);
           }
       });
       buttonhome.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(MainActivity.this, SmartHome.class);
               startActivity(intent);
           }
       });
    }
}