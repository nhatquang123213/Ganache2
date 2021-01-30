package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


public class Home extends AppCompatActivity {

    private ImageButton button;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        button = (ImageButton) findViewById(R.id.getstart);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          openLogin();
                                      }
                                  }
        );
        skip = (TextView) findViewById(R.id.skip);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoIndex(); Users.getInstance().setAddress("");
            }
        });

    }


    public  void openLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public  void gotoIndex(){
        Intent intent = new Intent(this, Index.class);
        startActivity(intent);
    }
}