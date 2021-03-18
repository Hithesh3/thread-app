package com.example.threadapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnAsync, btnHandler, btnTimer, btnRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAsync = (Button) findViewById(R.id.btnAsync);
        btnHandler = (Button) findViewById(R.id.btnHandler);
        btnTimer = (Button) findViewById(R.id.btnTimer);
        btnRun = (Button) findViewById(R.id.btnRun);

        btnAsync.setOnClickListener(this);
        btnHandler.setOnClickListener(this);
        btnTimer.setOnClickListener(this);
        btnRun.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAsync: {
                Intent intent = new Intent(MainActivity.this, Async.class);
                startActivity(intent);
                break;
            }
            case R.id.btnHandler: {
                Intent intent = new Intent(MainActivity.this, HandlerExample.class);
                startActivity(intent);
                break;
            }
            case R.id.btnTimer: {
                Intent intent = new Intent(MainActivity.this, TimerExample.class);
                startActivity(intent);
                break;
            }
            case R.id.btnRun: {
                Intent intent = new Intent(MainActivity.this, Run.class);
                startActivity(intent);
                break;
            }
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }

    }
}