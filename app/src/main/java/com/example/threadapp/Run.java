package com.example.threadapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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
import java.net.URLConnection;

public class Run extends AppCompatActivity implements View.OnClickListener {
    Button btnRunDownload;
    TextView tvRun;
    ProgressBar pbRun;
    ImageView ivRun;
    String url1 = "https://marketsimplified.com/wp-content/uploads/2020/01/msfLogo-011.png";
    Bitmap bitmap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);

        btnRunDownload = (Button) findViewById(R.id.btnRunDownload);
        tvRun = (TextView) findViewById(R.id.tvRun);
        pbRun = (ProgressBar) findViewById(R.id.pbRun);
        ivRun = (ImageView) findViewById(R.id.ivRun);

        btnRunDownload.setOnClickListener(this);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pbRun.setVisibility(View.VISIBLE);
                            tvRun.setText("Downloaing.....");
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivRun.setImageBitmap(bitmap);
                        if (bitmap == null) {
                            tvRun.setText("Download Incomplete Check Internet Connection");

                        }else{
                            tvRun.setText("Download Complete");
                        }
                        pbRun.setVisibility(View.INVISIBLE);
                        btnRunDownload.setEnabled(true);
                    }
                });
            }
        }).start();
    }
}