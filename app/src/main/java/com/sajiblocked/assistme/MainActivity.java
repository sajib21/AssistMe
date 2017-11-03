package com.sajiblocked.assistme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sajiblocked.assistme.alarm.AlarmActivity;

/**
 * Created by Shahriar Hossain Sajib on 10/28/2017.
 */

public class MainActivity extends AppCompatActivity {

    //TextView test;

    Button diaryButton;
    Button todoListButton;
    Button walletButton;
    Button alarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        diaryButton = (Button) findViewById(R.id.button_diary);
        todoListButton = (Button) findViewById(R.id.button_todolist);
        walletButton = (Button) findViewById(R.id.button_wallet);
        alarmButton = (Button) findViewById(R.id.button_alarm);

        diaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDiary(v);
            }
        });

        todoListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoToDoList(v);
            }
        });

        alarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoAlarm(v);
            }
        });

        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoWallet(v);
            }
        });
    }

    void gotoDiary(View view) {
        Intent intent = new Intent(this, DiaryUIActivity.class);
        startActivity(intent);
    }

    void gotoToDoList(View view) {
        Intent intent = new Intent(this, TodoListActivity.class);
        startActivity(intent);
    }

    void gotoAlarm(View view) {
        startActivity(new Intent(this, AlarmActivity.class));
    }

    void gotoWallet(View view) {
        Intent intent = new Intent(this, WalletActivity.class);
        startActivity(intent);
    }
}
