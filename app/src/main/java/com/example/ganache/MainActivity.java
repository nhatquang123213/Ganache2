package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ProgressBar pb;
    int counter =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prog();
    }
    public void prog(){
        pb =(ProgressBar) findViewById(R.id.pb);
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);
                if (counter==100){
                    t.cancel();
                    openHome();
                }
            }

        };
        t.schedule(tt,0,100);
    }
    public void openHome(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}