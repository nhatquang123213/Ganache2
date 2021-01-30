package com.example.ganache;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Signup extends AppCompatActivity {
    private ImageButton back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        back = findViewById(R.id.back_signup);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoLogin();
            }
        });
    }
    public void backtoLogin(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}