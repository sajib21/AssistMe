package com.sajiblocked.assistme.alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.provider.Settings;
import android.support.annotation.StringDef;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sajiblocked.assistme.R;

import java.sql.Time;
import java.util.Calendar;

/**
 * Created by Mahidul Islam Misbah.
 */

public class AlarmActivity extends AppCompatActivity {

    Button setAlarm,exit;
    EditText minute,hour;
    TextView textView,update;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);
        minute = (EditText) findViewById(R.id.minute);
        hour  = (EditText) findViewById(R.id.hour);
        textView = (TextView)findViewById(R.id.textview);
        setAlarm = (Button)findViewById(R.id.setAlarm);
        exit = (Button)findViewById(R.id.exit);
        update = (TextView)findViewById(R.id.update);

        final Calendar CurrentDateTime = Calendar.getInstance();

        setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //////////////  Alarm Time
                int h = Integer.parseInt(hour.getText().toString());
                int min = Integer.parseInt(minute.getText().toString());



                /////////   System Time
                int sec;
                int CurrentMinutes = CurrentDateTime.get(Calendar.MINUTE);
                int CurrentHours = CurrentDateTime.get(Calendar.HOUR);
                int Ampm = CurrentDateTime.get(Calendar.AM_PM);

                /////// 24 Hours format conversion////////////
                if(Ampm == 1)
                {
                    CurrentHours = CurrentHours+12;
                }

                if(h>CurrentHours || h==CurrentHours && min>CurrentMinutes)
                {
                    sec = (h*3600 + min*60)-(CurrentHours*3600 + CurrentMinutes*60);

                }
                else {
                    sec = ((CurrentHours + 24) * 3600 + CurrentMinutes * 60) - (h * 3600 + min * 60);
                }


                String hour_str = String.valueOf(h);
                String min_str = String.valueOf(min);

                if(h<10)
                    hour_str="0"+hour_str;
                if(h==0)
                    hour_str = "00";
                if (min < 10)
                    min_str = "0" + min_str;


                update.setText("Alarm set to " + hour_str +":"+ min_str);
                //String str = Integer.toString(sec);
                //Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();


                Intent intent = new Intent(AlarmActivity.this,Alarm_manager.class);
                AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),0,intent,0);
                alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+sec*1000,pendingIntent);

                //Toast.makeText(getApplicationContext(), "HOISE " , Toast.LENGTH_SHORT).show();
            }
        });



        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
