package com.example.threadapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HandlerExample extends AppCompatActivity implements View.OnClickListener {
    Button btnHandlerDownload;
    TextView tvHandler;
    ProgressBar pbHandler;
    ImageView ivHandler;
    String url1 = "https://marketsimplified.com/wp-content/uploads/2020/01/msfLogo-011.png";
    Bitmap bitmap = null;
    Handler handler = new Handler();

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        btnHandlerDownload = (Button) findViewById(R.id.btnHandlerDownload);
        tvHandler = (TextView) findViewById(R.id.tvHandler);
        pbHandler = (ProgressBar) findViewById(R.id.pbHandler);
        ivHandler = (ImageView) findViewById(R.id.ivHandler);
        btnHandlerDownload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        RunTask();
    }

    private void RunTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                URL url;
                HttpURLConnection http;
                InputStream in;
                try {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pbHandler.setVisibility(View.VISIBLE);
                            tvHandler.setText("Downloaing.....");
                        }
                    });
                    url = new URL(url1);
                    http = (HttpURLConnection) url.openConnection();
                    in = http.getInputStream();
                    bitmap = BitmapFactory.decodeStream(in);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ivHandler.setImageBitmap(bitmap);
                        if (bitmap == null) {
                            tvHandler.setText("Download Incomplete Check Internet Connection");

                        }else{
                            tvHandler.setText("Download Complete");
                        }
                        pbHandler.setVisibility(View.INVISIBLE);
                        btnHandlerDownload.setEnabled(true);
                    }
                });
            }
        }).start();
    }
}