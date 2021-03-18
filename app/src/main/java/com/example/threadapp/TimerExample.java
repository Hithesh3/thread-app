package com.example.threadapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class TimerExample extends AppCompatActivity implements View.OnClickListener {
    Button btnRotate;
    Timer timer;
    TimerTask task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btnRotate = (Button) findViewById(R.id.btnRotate);

        btnRotate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                btnRotate.setRotation(btnRotate.getRotation() + 10);
            }
        };
        timer.schedule(task, 1000, 1000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}